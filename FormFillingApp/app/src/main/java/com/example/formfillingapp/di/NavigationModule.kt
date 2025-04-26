package com.example.formfillingapp.di

import com.example.formfillingapp.navigation.NavigationHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt module for providing navigation-related dependencies
 */
@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {

    /**
     * Provides a NavigationHelper instance
     */
    @Provides
    @Singleton
    fun provideNavigationHelper(): NavigationHelper {
        return NavigationHelper()
    }
}