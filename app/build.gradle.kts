plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.sonar.qube)
}

android {
    namespace = "com.mario.omiechallenge"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mario.omiechallenge"
        minSdk = 24
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

sonar {
    properties {
        property("sonar.projectKey", "omiechallenge")
        property("sonar.projectName", "omiechallenge")
        property("sonar.projectVersion", "1.0.0")

        property("sonar.host.url", "http://localhost:9000")
        property("sonar.token", System.getenv("SONAR_TOKEN"))

        property(
            "sonar.sources",
            "/home/mario/AndroidStudioProjects/OmieChallenge/app/src/main/java"
        )

        property(
            "sonar.tests",
            "/home/mario/AndroidStudioProjects/OmieChallenge/app/src/test/java"
        )

        property("sonar.sourceEncoding", "UTF-8")
        property("sonar.java.source", "1.8")
    }
}

dependencies {

    implementation(libs.navigation.compose)
    implementation(libs.kotlin.serialization)

    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


}