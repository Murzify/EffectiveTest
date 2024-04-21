plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("effectivetest.android-app")
    id("effectivetest.android-ui")
    id("effectivetest.hilt")
}


dependencies {
    implementation(project(":core:ui"))
    implementation(project(":feature:flights"))
    implementation(project(":feature:stub"))

    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}