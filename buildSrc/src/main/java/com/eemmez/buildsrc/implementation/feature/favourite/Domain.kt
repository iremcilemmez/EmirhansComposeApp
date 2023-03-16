package com.eemmez.buildsrc.implementation.feature.favourite

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.Java
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val favouriteDomainDependencies = listOf(
    Dependency(":common:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"),
    Dependency("javax.inject:javax.inject:${Java.inject}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation)
)

fun DependencyHandler.favouriteDomainDependencies() {
    favouriteDomainDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.favouriteDomainPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
