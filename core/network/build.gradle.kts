plugins {
    id("effectivetest.android-lib")
    id("effectivetest.hilt")
    alias(libs.plugins.serialization)
}

android.namespace = "com.murzify.effectivetest.core.network"

dependencies {
    implementation(project(":core:domain"))

    implementation(libs.kotlinx.serialization)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}