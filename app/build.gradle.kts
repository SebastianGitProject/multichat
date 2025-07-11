plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.nonloso"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nonloso"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation ("com.google.dagger:hilt-android-gradle-plugin:2.42")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.google.dagger:hilt-android:2.45")
    implementation ("androidx.core:core-ktx:1.13.0")
    //kapt ("com.google.dagger:hilt-android-compiler:2.38.1")
    //kapt ("androidx.hilt:hilt-compiler:1.0.0")
    implementation ("androidx.activity:activity-ktx:1.9.0")

    //  Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation ("com.google.code.gson:gson:2.10.1")


    implementation("androidx.core:core-ktx:1.13.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("pl.droidsonroids.gif:android-gif-drawable:1.2.25")
    implementation ("com.android.volley:volley:1.2.1")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation ("com.github.markushi:circlebutton:1.1")
    implementation ("com.google.mlkit:language-id:17.0.5")


    implementation("androidx.cardview:cardview:1.0.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")

}