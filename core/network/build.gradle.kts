plugins {
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.anidoroido.android.library)
    alias(libs.plugins.anidoroido.hilt)
}

android {
    buildFeatures {
        buildConfig = true
    }

    namespace = "com.yzr.anidoroido.network"
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    api(projects.core.common)
    implementation(projects.core.model)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlinx.coroutines.core)
    testImplementation(libs.kotlinx.coroutines.test)
}