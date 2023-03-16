package com.eemmez.buildsrc.implementation.common

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val commonPresentationDependencies = listOf(
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.lifecycle:lifecycle-runtime-ktx:${Android.lifecycle}"),
    Dependency("androidx.lifecycle:lifecycle-runtime-compose:${Android.lifecycle}"),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}")
)

fun DependencyHandler.commonPresentationDependencies() {
    commonPresentationDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.commonPresentationPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}