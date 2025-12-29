package com.example.mqsquashers.main

import android.content.Context
import com.example.mqsquashers.R
import dev.hotwire.navigation.navigator.NavigatorConfiguration
import dev.hotwire.navigation.tabs.HotwireBottomTab
import com.example.mqsquashers.MQSquashers

fun getMainTabs(context: Context): List<HotwireBottomTab> {
    val ranking = HotwireBottomTab(
        title = context.getString(R.string.ranking),
        iconResId = R.drawable.leaderboard_24px,
        configuration = NavigatorConfiguration(
            name = "ranking",
            navigatorHostId = R.id.ranking_nav_host,
            startLocation = MQSquashers.current.url
        )
    )

    val finals = HotwireBottomTab(
        title = context.getString(R.string.finals),
        iconResId = R.drawable.trophy_24px,
        configuration = NavigatorConfiguration(
            name = "finals",
            navigatorHostId = R.id.finals_nav_host,
            startLocation = "${MQSquashers.current.url}/finals"
        )
    )

    val rules = HotwireBottomTab(
        title = context.getString(R.string.rules),
        iconResId = R.drawable.book_2_24px,
        configuration = NavigatorConfiguration(
            name = "resources",
            navigatorHostId = R.id.rules_nav_host,
            startLocation = "${MQSquashers.current.url}/pages/rules"
        )
    )

    val profile = HotwireBottomTab(
        title = context.getString(R.string.me),
        iconResId = R.drawable.person_24px,
        isVisible = true,
        configuration = NavigatorConfiguration(
            name = "profile",
            navigatorHostId = R.id.profile_nav_host,
            startLocation = "${MQSquashers.current.url}/me"
        )
    )

    return listOf(
        ranking,
        finals,
        rules,
        profile
    )
}
