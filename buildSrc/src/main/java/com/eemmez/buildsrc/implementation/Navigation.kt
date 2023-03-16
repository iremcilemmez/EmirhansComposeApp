package com.eemmez.buildsrc.implementation

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope
import org.gradle.plugin.use.PluginDependenciesSpec

private val navigationDependencies = listOf(
    Dependency(":feature:home:domain", config = Config.Project),
    Dependency(":feature:home:presentation", config = Config.Project),
    Dependency(":feature:favourite:domain", config = Config.Project),
    Dependency(":feature:favourite:presentation", config = Config.Project),
    Dependency(":feature:detail:domain", config = Config.Project),
    Dependency(":feature:detail:presentation", config = Config.Project),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}"),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.navigation:navigation-compose:${Android.navigation_compose}")
)

fun DependencyHandler.navigationDependencies() {
    navigationDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpec.navigationPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}
