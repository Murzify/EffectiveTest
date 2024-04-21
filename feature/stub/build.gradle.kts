plugins {
    id("effectivetest.android-lib")
    id("effectivetest.android-ui")
    id("effectivetest.hilt")
}

android.namespace = "com.murzify.effectivetest.feature.search"

dependencies {
    implementation(project(":core:ui"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}