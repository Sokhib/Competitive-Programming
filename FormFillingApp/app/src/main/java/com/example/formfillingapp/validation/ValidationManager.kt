package com.example.formfillingapp.validation

/**
 * Manages multiple validators for a single field
 *
 * @param T The type of value to validate
 * @property validators The list of validators to apply
 */
class ValidationManager<T> private constructor(
    private val validators: MutableList<Validator<T>>
) {
    /**
     * Validates the given value against all validators
     *
     * @param value The value to validate
     * @return The first failed validation result, or success if all validations pass
     */
    fun validate(value: T): ValidationResult {
        validators.forEach { validator ->
            val result = validator.validate(value)
            if (!result.isValid) {
                return result
            }
        }
        return ValidationResult.success()
    }

    /**
     * Adds a validator to this ValidationManager
     *
     * @param validator The validator to add
     * @return This ValidationManager for chaining
     */
    fun addValidator(validator: Validator<T>): ValidationManager<T> {
        validators.add(validator)
        return this
    }

    /**
     * Adds a validation function to this ValidationManager
     *
     * @param errorMessage The error message to show if validation fails
     * @param isValid The validation function that returns true if the value is valid
     * @return This ValidationManager for chaining
     */
    fun addValidation(errorMessage: String, isValid: (T) -> Boolean): ValidationManager<T> {
        validators.add(object : Validator<T> {
            override fun validate(value: T): ValidationResult {
                return if (isValid(value)) {
                    ValidationResult.success()
                } else {
                    ValidationResult.error(errorMessage)
                }
            }
        })
        return this
    }

    /**
     * Builder for creating a ValidationManager
     */
    class Builder<T> {
        private val validators = mutableListOf<Validator<T>>()

        /**
         * Adds a validator to the builder
         *
         * @param validator The validator to add
         * @return This builder for chaining
         */
        fun addValidator(validator: Validator<T>): Builder<T> {
            validators.add(validator)
            return this
        }

        /**
         * Adds a validation function to the builder
         *
         * @param errorMessage The error message to show if validation fails
         * @param isValid The validation function that returns true if the value is valid
         * @return This builder for chaining
         */
        fun addValidation(errorMessage: String, isValid: (T) -> Boolean): Builder<T> {
            validators.add(object : Validator<T> {
                override fun validate(value: T): ValidationResult {
                    return if (isValid(value)) {
                        ValidationResult.success()
                    } else {
                        ValidationResult.error(errorMessage)
                    }
                }
            })
            return this
        }

        /**
         * Builds a ValidationManager with the added validators
         *
         * @return A new ValidationManager
         */
        fun build(): ValidationManager<T> {
            return ValidationManager(validators)
        }
    }

    companion object {
        /**
         * Creates an empty ValidationManager
         *
         * @return A new ValidationManager with no validators
         */
        fun <T> create(): ValidationManager<T> {
            return ValidationManager(mutableListOf())
        }

        /**
         * Creates a ValidationManager with the specified validators
         *
         * @param validators The validators to include
         * @return A new ValidationManager with the specified validators
         */
        fun <T> of(vararg validators: Validator<T>): ValidationManager<T> {
            return ValidationManager(validators.toMutableList())
        }

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
        ): ValidationManager<String> {
            val validators = mutableListOf<Validator<String>>()

            if (required) {
                validators.add(NotBlankValidator(requiredMessage))
            }

            minLength?.let {
                validators.add(MinLengthValidator(it, minLengthMessage ?: "Must be at least $it characters"))
            }

            if (isEmail) {
                validators.add(EmailValidator(emailMessage))
            }

            pattern?.let {
                validators.add(PatternValidator(it, patternMessage))
            }

            return ValidationManager(validators)
        }
    }
}
