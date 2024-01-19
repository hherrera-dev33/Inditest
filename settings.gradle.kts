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
    }

    versionCatalogs {
        create("libs") {

            val hiltVersion = "2.50"
            val glideVersion = "4.16.0"
            val retrofitVersion = "2.9.0"
            val okHttpLoggingVersion = "4.9.0"

            val mapsVersion = "18.2.0"
            //androidx
            val coreVersion = "1.12.0"
            val navVersion = "2.7.6"
            val lifecycleVersion = "2.7.0"
            val splashVersion = "1.0.1"
            val constraintLayoutVersion = "2.1.4"
            val recyclerViewVersion = "1.3.2"
            val appCompatVersion = "1.6.1"
            val swipeRefreshLayoutVersion = "1.1.0"
            val pagingVersion = "3.2.1"

            //tests
            val junitVersion = "4.13.2"
            val androidXJunitVersion = "1.1.5"
            val androidXEspressoVersion = "3.5.1"

            library("hilt", "com.google.dagger:hilt-android:$hiltVersion")
            library("hilt-compiler", "com.google.dagger:hilt-android-compiler:$hiltVersion")

            library("glide", "com.github.bumptech.glide:glide:$glideVersion")

            library("retrofit", "com.squareup.retrofit2:retrofit:$retrofitVersion")
            library("retrofit-gson", "com.squareup.retrofit2:converter-gson:$retrofitVersion")
            library("okhttp-logging", "com.squareup.okhttp3:logging-interceptor:$okHttpLoggingVersion")

            library("googlemaps", "com.google.android.gms:play-services-maps:$mapsVersion")

            library("androidx-core", "androidx.core:core-ktx:$coreVersion")
            library("androidx-lifecycle", "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")
            library("androidx-navigation-fragment", "androidx.navigation:navigation-fragment-ktx:$navVersion")
            library("androidx-navigation-ui", "androidx.navigation:navigation-ui-ktx:$navVersion")
            library("androidx-splashscreen", "androidx.core:core-splashscreen:$splashVersion")
            library("androidx-constraintlayout", "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")
            library("androidx-recyclerview", "androidx.recyclerview:recyclerview:$recyclerViewVersion")
            library("androidx-appcompat", "androidx.appcompat:appcompat:$appCompatVersion")
            library("androidx-swiperefreshlayout", "androidx.swiperefreshlayout:swiperefreshlayout:$swipeRefreshLayoutVersion")
            library("androidx-paging-runtime", "androidx.paging:paging-runtime-ktx:$pagingVersion")
            library("androidx-paging-common", "androidx.paging:paging-common:$pagingVersion")

            //Tests
            library("tests.junit", "junit:junit:$junitVersion")
            library("androidx.paging.test", "androidx.paging:paging-testing:$pagingVersion")
            library("androidx.test.junit", "androidx.test.ext:junit:$androidXJunitVersion")
            library("androidx.test.espresso", "androidx.test.espresso:espresso-core:$androidXEspressoVersion")

        }
    }
}

rootProject.name = "Inditest"
include(":app")
include(":data")
include(":domain")
include(":di")
