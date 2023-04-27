package com.eemmez.buildsrc.implementation

import com.eemmez.buildsrc.Android
import com.eemmez.buildsrc.Config
import com.eemmez.buildsrc.Dependency
import com.eemmez.buildsrc.Google
import com.eemmez.buildsrc.Hilt
import com.eemmez.buildsrc.implementation
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.plugin.use.PluginDependenciesSpec

private val navigationDependencies = listOf(
    Dependency(":feature:home:domain", config = Config.Project),
    Dependency(":feature:home:presentation", config = Config.Project),
    Dependency(":feature:favourite:domain", config = Config.Project),
    Dependency(":feature:favourite:presentation", config = Config.Project),
    Dependency(":feature:detail:domain", config = Config.Project),
    Dependency(":feature:detail:presentation", config = Config.Project),
    Dependency("com.google.dagger:hilt-android:${Hilt.version}"),
    Dependency("com.google.dagger:hilt-compiler:${Hilt.version}", config = Config.Kapt),
    Dependency("androidx.hilt:hilt-navigation-compose:${Android.hilt_navigation_compose}"),
    Dependency("androidx.compose.ui:ui:${Android.compose}"),
    Dependency("androidx.compose.ui:ui-tooling-preview:${Android.compose}"),
    Dependency("androidx.compose.material3:material3:${Android.material3}"),
    Dependency("androidx.core:core-ktx:${Android.core_ktx}"),
    Dependency("androidx.navigation:navigation-compose:${Android.navigation_compose}"),
    Dependency("com.google.code.gson:gson:${Google.gson}"),
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
    ),
    Dependency(
        "androidx.navigation:navigation-testing:${Android.navigation_compose}",
        config = Config.AndroidTestImplementation
    ),
    Dependency(
        "com.google.dagger:hilt-android-testing:${Hilt.version}",
        config = Config.AndroidTestImplementation
    ),
    Dependency(
        "com.google.dagger:hilt-android-compiler:${Hilt.version}",
        config = Config.KaptAndroidTest
    )
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
