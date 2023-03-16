package com.eemmez.buildsrc.implementation.feature.detail

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Coil
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val detailPresentationDependencies = listOf(
    Dependency(":feature:detail:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.lifecycle:lifecycle-runtime-ktx:${Android.lifecycle}"),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}"),
    Dependency("io.coil-kt:coil-compose:${Coil.version}"),
    Dependency("junit:junit:${JUnit.version}", config = Config.TestImplementation),
    Dependency(
        "androidx.test.ext:junit:${Android.junit_ext}",
        config = Config.AndroidTestImplementation
    ),
    Dependency(
        "androidx.test.espresso:espresso-core:${Android.espresso}",
        config = Config.AndroidTestImplementation
    ),
    Dependency(
        "androidx.compose.ui:ui-test-junit4:${Android.compose}",
        config = Config.AndroidTestImplementation
    ),
    Dependency(
        "androidx.compose.ui:ui-tooling:${Android.compose}",
        config = Config.DebugImplementation
    ),
    Dependency(
        "androidx.compose.ui:ui-test-manifest:${Android.compose}",
        config = Config.DebugImplementation
    )
)

fun DependencyHandler.detailPresentationDependencies() {
    detailPresentationDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.detailPresentationPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}