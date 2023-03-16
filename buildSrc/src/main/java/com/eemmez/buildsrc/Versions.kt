package com.eemmez.buildsrc

import org.gradle.api.JavaVersion

object Project {
    const val version_code = 1
    const val version_name = "1.0"
}

object Gradle {
    const val version = "7.4.2"
}

object Kotlin {
    const val version = "1.8.10"
    const val compiler_version = "1.4.3"
    const val json_serialization = "1.5.0"
    const val coroutines = "1.6.4"
}

object Java {
    val version = JavaVersion.VERSION_18
    const val inject = 1
}

object Sdk {
    const val min = 24
    const val compile = 33
    const val target = 33
}

object Android {
    const val core_ktx = "1.9.0"
    const val lifecycle = "2.6.0"
    const val activity_compose= "1.6.1"
    const val compose = "1.3.3"
    const val material3 = "1.0.1"
    const val junit_ext = "1.1.5"
    const val espresso = "3.5.1"
    const val navigation_compose = "2.5.3"
    const val hilt_navigation_compose = "1.0.0"
    const val paging_compose = "1.0.0-alpha18"
}

object Hilt {
    const val version = "2.45"
}

object Coil {
    const val version = "2.2.2"
}

object Ktor {
    const val version = "2.2.4"
}

object Room {
    const val version = "2.5.0"
}

object LocalizationManager {
    const val version = "1.0"
}

object JUnit {
    const val version = "4.13.2"
}
