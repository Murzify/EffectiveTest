plugins {
    id("effectivetest.android-lib")
    id("effectivetest.hilt")
}

android.namespace = "com.murzify.effectivetest.core.shared_prefs"

dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}