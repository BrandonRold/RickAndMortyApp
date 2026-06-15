 plugins {
     id("com.android.application")
     alias(libs.plugins.jetbrains.kotlin.serialization)
     id("org.jetbrains.kotlin.plugin.compose") version "2.2.0"
     id("com.google.devtools.ksp")
     id("com.google.dagger.hilt.android")

}

android {
    namespace = "com.brs.rickyandmorthy"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.brs.rickyandmorthy"
        minSdk = 24
        //noinspection EditedTargetSdkVersion
        targetSdk = 37
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
    }
}

dependencies {



    implementation("androidx.compose.material:material-icons-extended")
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation3.ui)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.lifecycle.viewmodel.navigation3)

    testImplementation(libs.junit)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    debugImplementation(libs.androidx.compose.ui.tooling)

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")


    // Coroutines núcleo
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1")

    // Coroutines para Android (Dispatchers.Main)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

   // implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    //Moshi -> dependencia mejor que gson
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.0")


    //Okhttp
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.okhttp3:logging-interceptor:5.4.0")

    //hilt ->inyeccion de dependencias

    implementation("com.google.dagger:hilt-android:2.59.2")
    ksp("com.google.dagger:hilt-android-compiler:2.59.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //coil img
    implementation("io.coil-kt:coil-compose:2.6.0")


    //ROM
    val room_version = "2.8.4"
    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    // ViewModel + viewModelScope
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")

    // lifecycleScope
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // Coroutines test
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3")

    // Turbine para flujos
    testImplementation("app.cash.turbine:turbine:0.12.1")

    // Mocking
    testImplementation("io.mockk:mockk:1.13.5")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    // AndroidX Test (instrumented / compose)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    androidTestImplementation("androidx.test:core:1.5.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")



}