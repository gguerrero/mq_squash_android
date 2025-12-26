package com.example.mqsquashers

object MQSquashers {
    val current: Environment = Environment.Local

    enum class Environment(val url: String) {
        Remote("https://mqsquashers.com"),
        Local("http://192.168.2.145:3000")
    }
}