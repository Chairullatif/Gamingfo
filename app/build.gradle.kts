plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.chairullatif.gamingfo"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.chairullatif.gamingfo"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
        buildConfigField("String", "RAWG_KEY", "\"35b967dd980d447eb0382b7a5d04f7ba\"")
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
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}



dependencies {
    val kotlin_version = "1.9.20"
    val appcompat_version = "1.1.0"
    val core_ktx_version = "1.3.2"
    val constraint_version = "2.0.4"
    val legacy_support_version = "1.0.0"
    val junit_version = "4.13.1"
    val androidx_junit_version = "1.1.2"
    val espresso_version = "3.3.0"
    val multidex_version = "2.0.1"

    val cardview_version = "1.0.0"
    val recyclerview_version = "1.1.0"
    val material_version = "1.2.1"
    val glide_version = "4.11.0"

    val room_version = "2.2.5"

    val retrofit_version = "2.9.0"
    val logging_interceptor_version = "4.9.0"

    val rxjava_version = "2.2.19"
    val rxandroid_version = "2.1.1"
    val rxjava3_retrofit_adapter_version = "3.0.0"
    val lifecycle_version = "2.6.2"

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.github.bumptech.glide:glide:$glide_version")

    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    androidTestImplementation ("androidx.room:room-testing:$room_version")

    implementation ("com.squareup.retrofit2:retrofit:$retrofit_version")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation ("com.squareup.okhttp3:logging-interceptor:$logging_interceptor_version")

    implementation ("io.reactivex.rxjava2:rxjava:$rxjava_version")
    implementation ("io.reactivex.rxjava2:rxandroid:$rxandroid_version")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:$retrofit_version")
    implementation ("androidx.room:room-rxjava2:$room_version")
    implementation ("androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version")
}