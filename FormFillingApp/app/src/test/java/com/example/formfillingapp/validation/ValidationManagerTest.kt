package com.example.formfillingapp.validation

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidationManagerTest {

    @Test
    fun `validate returns success when all validators pass`() {
        // Given
        val validator1 = object : Validator<String> {
            override fun validate(value: String): ValidationResult = ValidationResult.success()
        }
        val validator2 = object : Validator<String> {
            override fun validate(value: String): ValidationResult = ValidationResult.success()
        }
        val manager = ValidationManager(listOf(validator1, validator2))

        // When
        val result = manager.validate("test")

        // Then
        assertTrue(result.isValid)
        assertNull(result.errorMessage)
    }

    @Test
    fun `validate returns first error when a validator fails`() {
        // Given
        val validator1 = object : Validator<String> {
            override fun validate(value: String): ValidationResult = ValidationResult.success()
        }
        val validator2 = object : Validator<String> {
            override fun validate(value: String): ValidationResult = ValidationResult.error("Error 2")
        }
        val validator3 = object : Validator<String> {
            override fun validate(value: String): ValidationResult = ValidationResult.error("Error 3")
        }
        val manager = ValidationManager(listOf(validator1, validator2, validator3))

        // When
        val result = manager.validate("test")

        // Then
        assertFalse(result.isValid)
        assertEquals("Error 2", result.errorMessage)
    }

    @Test
    fun `forString creates correct validators for required field`() {
        // Given
        val manager = ValidationManager.forString(required = true)

        // When & Then
        val emptyResult = manager.validate("")
        assertFalse(emptyResult.isValid)
        assertEquals("This field is required", emptyResult.errorMessage)

        val validResult = manager.validate("test")
        assertTrue(validResult.isValid)
        assertNull(validResult.errorMessage)
    }

    @Test
    fun `forString creates correct validators for min length`() {
        // Given
        val manager = ValidationManager.forString(minLength = 5)

        // When & Then
        val tooShortResult = manager.validate("test")
        assertFalse(tooShortResult.isValid)
        assertEquals("Must be at least 5 characters", tooShortResult.errorMessage)

        val validResult = manager.validate("tests")
        assertTrue(validResult.isValid)
        assertNull(validResult.errorMessage)
    }

    @Test
    fun `forString creates correct validators for email`() {
        // Given
        val manager = ValidationManager.forString(isEmail = true)

        // When & Then
        val invalidResult = manager.validate("not-an-email")
        assertFalse(invalidResult.isValid)
        assertEquals("Invalid email address", invalidResult.errorMessage)

        val validResult = manager.validate("test@example.com")
        assertTrue(validResult.isValid)
        assertNull(validResult.errorMessage)
    }

    @Test
    fun `forString creates correct validators for pattern`() {
        // Given
        val pattern = Regex("^[a-z]+$")
        val manager = ValidationManager.forString(pattern = pattern)

        // When & Then
        val invalidResult = manager.validate("123")
        assertFalse(invalidResult.isValid)
        assertEquals("Invalid format", invalidResult.errorMessage)

        val validResult = manager.validate("test")
        assertTrue(validResult.isValid)
        assertNull(validResult.errorMessage)
    }

    @Test
    fun `forString creates correct validators with custom messages`() {
        // Given
        val manager = ValidationManager.forString(
            required = true,
            minLength = 5,
            isEmail = true,
            pattern = Regex("^[a-z]+$"),
            requiredMessage = "Custom required message",
            minLengthMessage = "Custom min length message",
            emailMessage = "Custom email message",
            patternMessage = "Custom pattern message"
        )

        // When & Then
        val emptyResult = manager.validate("")
        assertFalse(emptyResult.isValid)
        assertEquals("Custom required message", emptyResult.errorMessage)

        val tooShortResult = manager.validate("test")
        assertFalse(tooShortResult.isValid)
        assertEquals("Custom min length message", tooShortResult.errorMessage)

        val invalidEmailResult = manager.validate("tests")
        assertFalse(invalidEmailResult.isValid)
        assertEquals("Custom email message", invalidEmailResult.errorMessage)

        val invalidPatternResult = manager.validate("test@example.com")
        assertFalse(invalidPatternResult.isValid)
        assertEquals("Custom pattern message", invalidPatternResult.errorMessage)

        val validResult = manager.validate("valid")
        assertTrue(validResult.isValid)
        assertNull(validResult.errorMessage)
    }
}