plugins {
    id("effectivetest.android-lib")
    id("effectivetest.hilt")
}

android.namespace = "com.murzify.effectivetest.core.data"

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:shared-prefs"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}