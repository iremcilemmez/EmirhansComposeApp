package com.eemmez.buildsrc.implementation.feature.favourite

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.Hilt
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.Room
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.kotlin.dsl.version

private val favouriteDataDependencies = listOf(
    Dependency(":common:data", config = Config.Project),
    Dependency(":common:domain", config = Config.Project),
    Dependency(":feature:favourite:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("com.google.dagger:hilt-android:${Hilt.version}"),
    Dependency("com.google.dagger:hilt-compiler:${Hilt.version}", config = Config.Kapt),
    Dependency("androidx.room:room-runtime:${Room.version}"),
    Dependency("androidx.room:room-ktx:${Room.version}"),
    Dependency("androidx.room:room-compiler:${Room.version}", config = Config.Kapt),
    Dependency("androidx.room:room-testing:${Room.version}", config = Config.TestImplementation),
    Dependency("org.jetbrains.kotlinx:kotlinx-serialization-json:${Kotlin.json_serialization}"),
    Dependency("junit:junit:${JUnit.version}")
)

fun DependencyHandler.favouriteDataDependencies() {
    favouriteDataDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.favouriteDataPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}