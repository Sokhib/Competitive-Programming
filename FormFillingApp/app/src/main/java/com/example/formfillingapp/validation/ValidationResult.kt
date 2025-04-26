package com.example.formfillingapp.validation

/**
 * Represents the result of a validation check
 *
 * @property isValid Whether the validation passed
 * @property errorMessage The error message if validation failed, null otherwise
 */
data class ValidationResult(
    val isValid: Boolean,
    val errorMessage: String? = null
) {
    companion object {
        /**
         * Creates a successful validation result
         */
        fun success(): ValidationResult = ValidationResult(isValid = true)

        /**
         * Creates a failed validation result with the given error message
         */
        fun error(message: String): ValidationResult = ValidationResult(isValid = false, errorMessage = message)
    }
}