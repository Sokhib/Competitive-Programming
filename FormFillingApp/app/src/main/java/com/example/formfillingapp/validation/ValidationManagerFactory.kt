package com.example.formfillingapp.validation

/**
 * Factory interface for creating ValidationManager instances
 */
interface ValidationManagerFactory {
    /**
     * Creates an empty ValidationManager
     *
     * @return A new ValidationManager with no validators
     */
    fun <T> create(): ValidationManager<T>

    /**
     * Creates a ValidationManager with the specified validators
     *
     * @param validators The validators to include
     * @return A new ValidationManager with the specified validators
     */
    fun <T> of(vararg validators: Validator<T>): ValidationManager<T>

    /**
     * Creates a ValidationManager for a string field with common validators
     *
     * @param required Whether the field is required
     * @param minLength The minimum length required, or null for no minimum
     * @param isEmail Whether to validate as an email address
     * @param pattern A regex pattern to match, or null for no pattern matching
     * @param requiredMessage The error message for required validation
     * @param minLengthMessage The error message for minimum length validation
     * @param emailMessage The error message for email validation
     * @param patternMessage The error message for pattern validation
     * @return A ValidationManager with the specified validators
     */
    fun forString(
        required: Boolean = false,
        minLength: Int? = null,
        isEmail: Boolean = false,
        pattern: Regex? = null,
        requiredMessage: String = "This field is required",
        minLengthMessage: String? = null,
        emailMessage: String = "Invalid email address",
        patternMessage: String = "Invalid format"
    ): ValidationManager<String>
}