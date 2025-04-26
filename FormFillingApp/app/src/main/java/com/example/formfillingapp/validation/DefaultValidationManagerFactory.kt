package com.example.formfillingapp.validation

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Default implementation of ValidationManagerFactory
 * Delegates to ValidationManager's companion object methods
 */
@Singleton
class DefaultValidationManagerFactory @Inject constructor() : ValidationManagerFactory {

    override fun <T> create(): ValidationManager<T> {
        return ValidationManager.create()
    }

    override fun <T> of(vararg validators: Validator<T>): ValidationManager<T> {
        return ValidationManager.of(*validators)
    }

    override fun forString(
        required: Boolean,
        minLength: Int?,
        isEmail: Boolean,
        pattern: Regex?,
        requiredMessage: String,
        minLengthMessage: String?,
        emailMessage: String,
        patternMessage: String
    ): ValidationManager<String> {
        return ValidationManager.forString(
            required = required,
            minLength = minLength,
            isEmail = isEmail,
            pattern = pattern,
            requiredMessage = requiredMessage,
            minLengthMessage = minLengthMessage,
            emailMessage = emailMessage,
            patternMessage = patternMessage
        )
    }
}