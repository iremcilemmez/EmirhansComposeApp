package com.eemmez.buildsrc.implementation.common

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.Hilt
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.Ktor
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.version

private val commonDataDependencies = listOf(
    Dependency(":common:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("com.google.dagger:hilt-android:${Hilt.version}"),
    Dependency("com.google.dagger:hilt-compiler:${Hilt.version}", config = Config.Kapt),
    Dependency("io.ktor:ktor-client-core:${Ktor.version}"),
    Dependency("io.ktor:ktor-client-auth:${Ktor.version}"),
    Dependency("io.ktor:ktor-client-android:${Ktor.version}"),
    Dependency("io.ktor:ktor-client-content-negotiation:${Ktor.version}"),
    Dependency("io.ktor:ktor-serialization-kotlinx-json:${Ktor.version}"),
    Dependency("io.ktor:ktor-client-logging:${Ktor.version}"),
    Dependency("org.jetbrains.kotlinx:kotlinx-serialization-json:${Kotlin.json_serialization}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation)
)

fun DependencyHandler.commonDataDependencies() {
    commonDataDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.commonDataPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.22"
}