
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.2.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.android.library") version "8.2.1" apply false
    id("org.jetbrains.kotlin.jvm") version "1.9.0" apply false
    id("com.google.dagger.hilt.android") version "2.50" apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        val nav_version = "2.7.6"
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
    }
}