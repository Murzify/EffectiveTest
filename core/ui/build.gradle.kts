plugins {
    id("effectivetest.android-lib")
    id("effectivetest.android-ui")
}

android.namespace = "com.murzify.effectivetest.core.ui"


dependencies {
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}