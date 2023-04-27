import com.eemmez.buildsrc.Java
import com.eemmez.buildsrc.Kotlin
import com.eemmez.buildsrc.Sdk
import com.eemmez.buildsrc.implementation.navigationDependencies

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.eemmez.navigation"
    compileSdk = Sdk.compile

    defaultConfig {
        minSdk = Sdk.min
        targetSdk = Sdk.target

        testInstrumentationRunner = "com.eemmez.navigation.HiltTestRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    packaging {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    navigationDependencies()
}