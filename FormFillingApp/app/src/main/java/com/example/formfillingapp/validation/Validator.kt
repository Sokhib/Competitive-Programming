package com.example.formfillingapp.validation

/**
 * Interface for validators
 */
interface Validator<T> {
    /**
     * Validates the given value
     *
     * @param value The value to validate
     * @return The validation result
     */
    fun validate(value: T): ValidationResult
}