plugins {
    alias(libs.plugins.anidoroido.android.library)
    alias(libs.plugins.anidoroido.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.yzr.anidoroido.data"
    testOptions.unitTests.isIncludeAndroidResources = true
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(libs.kotlinx.coroutines.core)
}