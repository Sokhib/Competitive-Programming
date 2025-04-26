package com.example.formfillingapp.di

import com.example.formfillingapp.utils.DateTimeFormatter
import com.example.formfillingapp.utils.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing utility-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object UtilsModule {

    /**
     * Provides a Logger instance
     */
    @Provides
    @Singleton
    fun provideLogger(): Logger {
        return Logger()
    }

    /**
     * Provides a DateTimeFormatter instance
     */
    @Provides
    @Singleton
    fun provideDateTimeFormatter(): DateTimeFormatter {
        return DateTimeFormatter()
    }
}