package com.example.mqsquashers

import android.app.Application
import android.util.Log
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration

class MQSquashersApplication: Application() {
    var isDebug: Boolean = MQSquashers.current == MQSquashers.Environment.Local

    override fun onCreate() {
        super.onCreate()
        configureApp()
    }

    private fun configureApp() {
        // Loads the path configuration
        Hotwire.loadPathConfiguration(
            context = this,
            location = PathConfiguration.Location(
                assetFilePath = "json/path-configuration.json",
                remoteFileUrl = "${MQSquashers.current.url}/json/android_v1.json"
            )
        )

        // Set configuration options
        Hotwire.config.debugLoggingEnabled = isDebug
        Hotwire.config.webViewDebuggingEnabled = isDebug
        Hotwire.config.jsonConverter = KotlinXJsonConverter()
        Hotwire.config.applicationUserAgentPrefix =
            "${getString(R.string.full_app_name)}/${getString(R.string.app_version)}"

        // Logging for Debug configuration
        Log.d(
            "MQSquashersApplication",
            ": Hotwire Config userAgent: ${Hotwire.config.userAgent}"
        )
    }
}