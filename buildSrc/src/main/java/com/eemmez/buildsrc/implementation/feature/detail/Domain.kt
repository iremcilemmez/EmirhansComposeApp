package com.eemmez.buildsrc.implementation.feature.detail

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val detailDomainDependencies = listOf(
    Dependency(":common:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation)
)

fun DependencyHandler.detailDomainDependencies() {
    detailDomainDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.detailDomainPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}