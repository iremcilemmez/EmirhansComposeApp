package com.eemmez.buildsrc.implementation.feature.favourite

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Coil
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.Hilt
import com.eemmez.buildsrc.JUnit
import com.eemmez.buildsrc.LocalizationManager
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.PluginDependenciesSpecScope

private val favouritePresentationDependencies = listOf(
    Dependency(":common:domain", config = Config.Project),
    Dependency(":common:presentation", config = Config.Project),
    Dependency(":feature:favourite:domain", config = Config.Project),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.lifecycle:lifecycle-runtime-ktx:${Android.lifecycle}"),
    Dependency("androidx.lifecycle:lifecycle-runtime-compose:${Android.lifecycle}"),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}"),
    Dependency("com.google.dagger:hilt-android:${Hilt.version}"),
    Dependency("com.google.dagger:hilt-compiler:${Hilt.version}", config = Config.Kapt),
    Dependency("androidx.hilt:hilt-navigation-compose:${Android.hilt_navigation_compose}"),
    Dependency("io.coil-kt:coil-compose:${Coil.version}"),
    Dependency("com.github.emirhanemmez:localizationmanager:${LocalizationManager.version}"),
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

fun DependencyHandler.favouritePresentationDependencies() {
    favouritePresentationDependencies.forEach {
        implementation(it)
    }
}

fun PluginDependenciesSpecScope.favouritePresentationPlugins() {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}