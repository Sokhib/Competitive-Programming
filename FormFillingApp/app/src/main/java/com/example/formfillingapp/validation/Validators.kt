package com.example.formfillingapp.validation

import android.util.Patterns
import javax.inject.Inject

/**
 * Validates that a string is not blank
 *
 * @property errorMessage The error message to show if validation fails
 */
class NotBlankValidator @Inject constructor(
    private val errorMessage: String = "This field is required"
) : Validator<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.isBlank()) {
            ValidationResult.error(errorMessage)
        } else {
            ValidationResult.success()
        }
    }
}

/**
 * Validates that a string has a minimum length
 *
 * @property minLength The minimum length required
 * @property errorMessage The error message to show if validation fails
 */
class MinLengthValidator @Inject constructor(
    private val minLength: Int,
    private val errorMessage: String = "Must be at least $minLength characters"
) : Validator<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.length < minLength) {
            ValidationResult.error(errorMessage)
        } else {
            ValidationResult.success()
        }
    }
}

/**
 * Validates that a string is a valid email address
 *
 * @property errorMessage The error message to show if validation fails
 */
class EmailValidator @Inject constructor(
    private val errorMessage: String = "Invalid email address"
) : Validator<String> {
    override fun validate(value: String): ValidationResult {
        return if (value.isBlank() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            ValidationResult.error(errorMessage)
        } else {
            ValidationResult.success()
        }
    }
}

/**
 * Validates that a string matches a pattern
 *
 * @property pattern The regex pattern to match
 * @property errorMessage The error message to show if validation fails
 */
class PatternValidator @Inject constructor(
    private val pattern: Regex,
    private val errorMessage: String = "Invalid format"
) : Validator<String> {
    override fun validate(value: String): ValidationResult {
        return if (!pattern.matches(value)) {
            ValidationResult.error(errorMessage)
        } else {
            ValidationResult.success()
        }
    }
}
