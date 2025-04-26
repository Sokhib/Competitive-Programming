package com.example.formfillingapp.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Hilt module for providing application-level dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * This module is for application-wide dependencies.
     *
     * Examples of dependencies that could be provided here:
     * - Network clients (Retrofit, OkHttp)
     * - Database instances (Room)
     * - SharedPreferences
     * - Repository implementations
     * - Other application-wide services
     */
}
