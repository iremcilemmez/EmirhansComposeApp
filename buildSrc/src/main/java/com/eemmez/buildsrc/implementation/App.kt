package com.eemmez.buildsrc.implementation

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.Hilt
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val appDependencies = listOf(
    Dependency(":common:data", config = Config.Project),
    Dependency(":common:domain", config = Config.Project),
    Dependency(":common:presentation", config = Config.Project),
    Dependency(":navigation", config = Config.Project),
    Dependency(":feature:home:data", config = Config.Project),
    Dependency(":feature:home:domain", config = Config.Project),
    Dependency(":feature:home:presentation", config = Config.Project),
    Dependency(":feature:favourite:data", config = Config.Project),
    Dependency(":feature:favourite:domain", config = Config.Project),
    Dependency(":feature:favourite:presentation", config = Config.Project),
    Dependency(":feature:detail:domain", config = Config.Project),
    Dependency(":feature:detail:presentation", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.activity:activity-compose:${Android.activity_compose}"),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}"),
    Dependency("com.google.dagger:hilt-android:${Hilt.version}"),
    Dependency("com.google.dagger:hilt-compiler:${Hilt.version}", config = Config.Kapt)
)

fun DependencyHandler.appDependencies() {
    appDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.appPlugins() {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}