import com.eemmez.buildsrc.Java
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.Project
import com.eemmez.buildsrc.Sdk
import com.eemmez.buildsrc.implementation.appDependencies

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.eemmez.mycomposeapp"
    compileSdk = Sdk.compile

    defaultConfig {
        applicationId = "com.eemmez.mycomposeapp"
        minSdk = Sdk.min
        targetSdk = Sdk.target
        versionCode = Project.version_code
        versionName = Project.version_name

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = Java.version
        targetCompatibility = Java.version
    }
    kotlinOptions {
        jvmTarget = Java.version.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Kotlin.compiler_version
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1,INDEX.LIST}")
        }
    }
}

dependencies {
    appDependencies()
}