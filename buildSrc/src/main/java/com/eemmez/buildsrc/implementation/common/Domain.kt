package com.eemmez.buildsrc.implementation.common

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.Java
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val commonDomainDependencies = listOf(
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"),
    Dependency("javax.inject:javax.inject:${Java.inject}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation)
)

fun DependencyHandler.commonDomainDependencies() {
    commonDomainDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.commonDomainPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
