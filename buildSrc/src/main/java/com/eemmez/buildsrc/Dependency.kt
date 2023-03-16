package com.eemmez.buildsrc

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

data class Dependency(
    val name: String,
    val config: Config = Config.Implementation
)

sealed class Config(val name: String) {
    object Implementation : Config("implementation")
    object Kapt : Config("kapt")
    object Project : Config("implementation")
    object Platform : Config("implementation")
    object Classpath : Config("classpath")
    object TestImplementation : Config("testImplementation")
    object AndroidTestImplementation : Config("androidTestImplementation")
    object DebugImplementation : Config("debugImplementation")
}

fun DependencyHandler.implementation(dependency: Dependency) {
    when (dependency.config) {
        Config.Project -> add(dependency.config.name, project(dependency.name))
        Config.Platform -> add(dependency.config.name, platform(dependency.name))
        else -> add(dependency.config.name, dependency.name)
    }
}
