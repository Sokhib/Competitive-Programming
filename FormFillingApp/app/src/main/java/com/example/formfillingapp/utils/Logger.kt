package com.example.formfillingapp.utils

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Simple logger utility that can be injected
 */
@Singleton
class Logger @Inject constructor() {

    /**
     * Log a debug message
     *
     * @param tag The tag to use for the log message
     * @param message The message to log
     */
    fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    /**
     * Log an info message
     *
     * @param tag The tag to use for the log message
     * @param message The message to log
     */
    fun info(tag: String, message: String) {
        Log.i(tag, message)
    }

    /**
     * Log an error message
     *
     * @param tag The tag to use for the log message
     * @param message The message to log
     * @param throwable The throwable to log, if any
     */
    fun error(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
    }

    /**
     * Log a warning message
     *
     * @param tag The tag to use for the log message
     * @param message The message to log
     */
    fun warn(tag: String, message: String) {
        Log.w(tag, message)
    }
}