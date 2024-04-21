import com.android.build.gradle.BaseExtension

configure<BaseExtension> {
    buildFeatures.viewBinding = true
}

dependencies {
    "implementation"(libs.androidx.core.ktx)
    "implementation"(libs.material)
    "implementation"(libs.androidx.appcompat)
    "implementation"(libs.androidx.lifcycle.viewmodel)
    "implementation"(libs.androidx.lifcycle.runtime)
    "implementation"(libs.androidx.constraintlayout)
    "implementation"(libs.androidx.fragment)

}