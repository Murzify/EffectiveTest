import com.android.build.gradle.BaseExtension

plugins {
    id("com.android.application")
    id("effectivetest.android")
}

configure<BaseExtension> {
    namespace = "com.murzify.effectivetest"
    defaultConfig {
        applicationId = "com.murzify.effectivetest"
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
}