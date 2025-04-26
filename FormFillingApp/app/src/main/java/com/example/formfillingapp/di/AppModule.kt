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
    // Add dependencies here as needed
    // For example:
    // @Provides
    // @Singleton
    // fun provideRepository(): Repository {
    //     return RepositoryImpl()
    // }
}