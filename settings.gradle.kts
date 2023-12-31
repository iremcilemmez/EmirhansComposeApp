pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url = "https://jitpack.io")
    }
}
rootProject.name = "MyComposeApp"
include(":app")
include(":common:data")
include(":common:domain")
include(":common:presentation")
include(":navigation")
include(":feature:home:data")
include(":feature:home:domain")
include(":feature:home:presentation")
include(":feature:favourite:data")
include(":feature:favourite:domain")
include(":feature:favourite:presentation")
include(":feature:detail:domain")
include(":feature:detail:presentation")
