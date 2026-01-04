package com.mqsquashers.app.main

import android.animation.ObjectAnimator
import android.app.Activity
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

fun installSplash(activity: Activity): SplashScreen {
    // Install splash screen
    val splashScreen = activity.installSplashScreen()

    // Customize animation
    splashScreen.setOnExitAnimationListener { splashScreenView ->
        val slideUp = ObjectAnimator.ofFloat(
            splashScreenView.iconView,
            View.TRANSLATION_Y,
            0f,
            -splashScreenView.iconView.height.toFloat()
        )
        slideUp.interpolator = AnticipateInterpolator()
        slideUp.duration = 500L

        slideUp.doOnEnd { splashScreenView.remove() }
        slideUp.start()
    }

    return splashScreen
}