plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
}

android {
    namespace = "com.geeks.hw_3"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.geeks.hw_3"
        minSdk = 28
        targetSdk = 36
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
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navHost
    val navVersion = "2.9.6"
    implementation("androidx.navigation:navigation-fragment:${navVersion}")
    implementation("androidx.navigation:navigation-ui:${navVersion}")

    //lottie
    val lottieVersion = "6.6.6"
    implementation("com.airbnb.android:lottie:$lottieVersion")

    //circleIndicator
    implementation("me.relex:circleindicator:2.1.6")

}