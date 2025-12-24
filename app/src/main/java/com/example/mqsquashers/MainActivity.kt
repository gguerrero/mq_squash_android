package com.example.mqsquashers

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.core.animation.doOnEnd
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets

class MainActivity : HotwireActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Hotwire.config.applicationUserAgentPrefix =
            "${getString(R.string.full_app_name)}/${getString(R.string.app_version)}"
        Log.d("MainActivity", "Hotwire Config userAgent: ${Hotwire.config.userAgent}")

        installSplash()

        // Loads the path configuration
        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                assetFilePath = "config/path_configuration_v1.json",
                remoteFileUrl = "${getString(R.string.main_nav_uri)}/configurations/android_v1.json"
            )
        )

        // Create main content view layout?
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.main_nav_host).applyDefaultImeWindowInsets()

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_nav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_ranking -> {
                    Log.d("MainActivity", "Ranking selected")
                    // TODO: Navigate to ranking path
                    true
                }
                R.id.nav_finals -> {
                    Log.d("MainActivity", "Finals selected")
                    // TODO: Navigate to finals path
                    true
                }
                R.id.nav_rules -> {
                    Log.d("MainActivity", "Rules selected")
                    // TODO: Navigate to rules path
                    true
                }
                R.id.nav_me -> {
                    Log.d("MainActivity", "Me selected")
                    // TODO: Navigate to me path
                    true
                }
                else -> false
            }
        }
    }

    override fun navigatorConfigurations() = listOf(
        NavigatorConfiguration(
            name = "main",
            startLocation = getString(R.string.main_nav_uri),
            navigatorHostId = R.id.main_nav_host
        )
    )

    private fun installSplash() {
        // Install splash screen
        val splashScreen = installSplashScreen()

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
    }
}