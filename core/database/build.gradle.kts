plugins {
    alias(libs.plugins.anidoroido.android.library)
    alias(libs.plugins.anidoroido.hilt)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.yzr.anidoroido.database"
}

dependencies {
    api(projects.core.model)

    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)

    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.test.ruler)
    androidTestImplementation(libs.kotlinx.coroutines.test)
}