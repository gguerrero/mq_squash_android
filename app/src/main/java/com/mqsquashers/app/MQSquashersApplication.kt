package com.mqsquashers.app

import android.app.Application
import android.util.Log
import dev.hotwire.core.bridge.BridgeComponentFactory
import dev.hotwire.core.bridge.KotlinXJsonConverter
import dev.hotwire.core.config.Hotwire
import dev.hotwire.core.turbo.config.PathConfiguration
import dev.hotwire.navigation.config.registerBridgeComponents
import com.mqsquashers.app.bridge.MenuComponent
import com.mqsquashers.app.bridge.OverflowMenuComponent
import com.mqsquashers.app.features.web.WebFragment
import dev.hotwire.navigation.config.defaultFragmentDestination
import dev.hotwire.navigation.config.registerFragmentDestinations

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

        // Set the default fragment destination
        Hotwire.defaultFragmentDestination = WebFragment::class

        // Register fragment destinations
        Hotwire.registerFragmentDestinations(
            WebFragment::class
        )

        // Register bridge components
        Hotwire.registerBridgeComponents(
            BridgeComponentFactory("menu", ::MenuComponent),
            BridgeComponentFactory("overflow-menu", ::OverflowMenuComponent)
        )

        // Set configuration options
        Hotwire.config.debugLoggingEnabled = isDebug
        Hotwire.config.webViewDebuggingEnabled = isDebug
        Hotwire.config.jsonConverter = KotlinXJsonConverter()
        Hotwire.config.applicationUserAgentPrefix =
            "${getString(R.string.full_app_name)}/${getString(R.string.app_version)}"
    }
}