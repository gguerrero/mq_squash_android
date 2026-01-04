package com.mqsquashers.app.main

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.mqsquashers.app.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import dev.hotwire.navigation.activities.HotwireActivity
import dev.hotwire.navigation.tabs.HotwireBottomNavigationController
import dev.hotwire.navigation.tabs.HotwireBottomTab
import dev.hotwire.navigation.tabs.navigatorConfigurations
import dev.hotwire.navigation.util.applyDefaultImeWindowInsets
import kotlin.getValue

class MainActivity : HotwireActivity() {
    private lateinit var bottomNavigationController: HotwireBottomNavigationController
    private lateinit var mainTabs: List<HotwireBottomTab>
    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Custom splash animation for this activity
        installSplash(this)

        // Loads navigation tabs configuration
        mainTabs = getMainTabs(this)

        // Enable edge to edge application view
        enableEdgeToEdge()

        // Create main content view layout?
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        findViewById<View>(R.id.root).applyDefaultImeWindowInsets()

        // Initialize bottom tabs, the botton nav config should be initialize at this point
        initializeBottomTabs()
    }

    override fun navigatorConfigurations() = mainTabs.navigatorConfigurations

    private fun initializeBottomTabs() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        bottomNavigationController = HotwireBottomNavigationController(
            this,
            bottomNavigationView
        )
        bottomNavigationController.load(mainTabs, viewModel.selectedTabIndex)
        bottomNavigationController.setOnTabSelectedListener { index, _ ->
            viewModel.selectedTabIndex = index
        }
    }
}