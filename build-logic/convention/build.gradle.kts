plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.gradle)
    implementation(libs.kotlin.gradle)
    implementation(libs.kotlin.ksp.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

gradlePlugin {
    plugins {
        register("hilt") {
            id = "effectivetest.hilt"
            implementationClass = "com.murzify.effectivetest.buildlogic.convention.HIltPlugin"
        }
    }
}

