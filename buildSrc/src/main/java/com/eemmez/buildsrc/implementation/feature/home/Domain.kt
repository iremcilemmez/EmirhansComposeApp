package com.eemmez.buildsrc

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.Java
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val homeDomainDependencies = listOf(
    Dependency(":common:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"),
    Dependency("javax.inject:javax.inject:${Java.inject}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation)
)

fun DependencyHandler.homeDomainDependencies() {
    homeDomainDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.homeDomainPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
}
