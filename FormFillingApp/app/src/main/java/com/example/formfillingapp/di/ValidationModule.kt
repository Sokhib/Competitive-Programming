package com.example.formfillingapp.di

import com.example.formfillingapp.validation.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

/**
 * Hilt module for providing validation-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    /**
     * Provides a ValidationManagerFactory for creating ValidationManager instances
     */
    @Provides
    @Singleton
    fun provideValidationManagerFactory(): ValidationManagerFactory {
        return DefaultValidationManagerFactory()
    }

    /**
     * Provides a NotBlankValidator with default error message
     */
    @Provides
    fun provideNotBlankValidator(): NotBlankValidator {
        return NotBlankValidator()
    }

    /**
     * Provides a MinLengthValidator for password validation
     */
    @Provides
    fun provideMinLengthValidator(): MinLengthValidator {
        return MinLengthValidator(6, "Password must be at least 6 characters")
    }

    /**
     * Provides an EmailValidator
     */
    @Provides
    fun provideEmailValidator(): EmailValidator {
        return EmailValidator()
    }
}
