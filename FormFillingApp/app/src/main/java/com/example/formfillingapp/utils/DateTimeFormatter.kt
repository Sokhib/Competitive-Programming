package com.example.formfillingapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Utility for formatting dates and times
 */
@Singleton
class DateTimeFormatter @Inject constructor() {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    private val dateTimeFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

    /**
     * Format a date
     *
     * @param date The date to format
     * @return The formatted date string
     */
    fun formatDate(date: Date): String {
        return dateFormat.format(date)
    }

    /**
     * Format a time
     *
     * @param date The date to extract the time from
     * @return The formatted time string
     */
    fun formatTime(date: Date): String {
        return timeFormat.format(date)
    }

    /**
     * Format a date and time
     *
     * @param date The date and time to format
     * @return The formatted date and time string
     */
    fun formatDateTime(date: Date): String {
        return dateTimeFormat.format(date)
    }

    /**
     * Get the current date and time as a formatted string
     *
     * @return The current date and time as a formatted string
     */
    fun getCurrentDateTime(): String {
        return formatDateTime(Date())
    }
}