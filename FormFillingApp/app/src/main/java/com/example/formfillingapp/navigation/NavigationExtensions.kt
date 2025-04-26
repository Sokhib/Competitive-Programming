package com.example.formfillingapp.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry

/**
 * Extension functions for navigation transitions
 */

/**
 * Default animation duration for transitions
 */
const val DEFAULT_ANIMATION_DURATION = 300

/**
 * Slide in from the right
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInFromRight(
    duration: Int = DEFAULT_ANIMATION_DURATION
): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(duration)
    )
}

/**
 * Slide in from the left
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideInFromLeft(
    duration: Int = DEFAULT_ANIMATION_DURATION
): EnterTransition {
    return slideIntoContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(duration)
    )
}

/**
 * Slide out to the left
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutToLeft(
    duration: Int = DEFAULT_ANIMATION_DURATION
): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(duration)
    )
}

/**
 * Slide out to the right
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.slideOutToRight(
    duration: Int = DEFAULT_ANIMATION_DURATION
): ExitTransition {
    return slideOutOfContainer(
        towards = AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(duration)
    )
}