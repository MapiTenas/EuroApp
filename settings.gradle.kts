pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            authentication {
                create<BasicAuthentication>("basic")
            }
            credentials {
                username = "mapbox"
                password = "sk.eyJ1IjoibWFwaXRlbmFzIiwiYSI6ImNsd3FoaXcwbTAxd3IyanF2aXhiamF0cGgifQ.0oOaBboTiiJlmOixdewqDw"
            }
        }
    }
}

rootProject.name = "EuroApp"
include(":app")
 