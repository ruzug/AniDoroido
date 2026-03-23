plugins {
    alias(libs.plugins.anidoroido.jvm.library)
    alias(libs.plugins.anidoroido.hilt)
}
dependencies {
    implementation(libs.kotlinx.coroutines.core)
}