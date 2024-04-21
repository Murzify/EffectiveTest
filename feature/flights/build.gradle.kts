plugins {
    id("effectivetest.android-lib")
    id("effectivetest.android-ui")
    id("effectivetest.hilt")
    id("androidx.navigation.safeargs.kotlin")
}

android.namespace = "com.murzify.effectivetest.featre.flights"

dependencies {
    implementation(project(":core:ui"))
    implementation(project(":core:domain"))
    implementation(project(":core:data"))
    implementation(project(":core:common"))

    implementation(libs.adapderdelegate)
    implementation(libs.adapderdelegate.viewbinding)

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}