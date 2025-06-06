import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.google.gms.google.services)

}

android {
    namespace = "com.dan.comidarf"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.dan.comidarf"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")
        if(localPropertiesFile.exists()){
            localProperties.load(localPropertiesFile.inputStream())
        }

        val mapsApiKey = localProperties.getProperty("MAPS_API_KEY")

        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
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


//Para retrofit y Gson
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

//Adicional para el interceptor
    implementation(libs.logging.interceptor)

//Glide y Picasso
    implementation(libs.glide)
    implementation(libs.picasso)

//Para las corrutinas con alcance lifecycle (opcional)
    implementation(libs.androidx.lifecycle.runtime.ktx)

//Imágenes con bordes redondeados
    implementation(libs.roundedimageview)

    implementation (libs.androidx.core.splashscreen)

    implementation (libs.androidyoutubeplayer.core)

    //Bibliotecas para Credential Manager
    implementation(libs.androidx.credentials)
    implementation(libs.androidx.credentials.play.services.auth)
    implementation(libs.googleid)

    //Google Maps (Play services de Google Maps, tanto para vistas XML como para Compose)
    implementation("com.google.android.gms:play-services-maps:19.2.0")

//API'S opcionales para la ubicación (XML y Compose). Ej. Clase FusedLocationProviderClient
    implementation("com.google.android.gms:play-services-location:21.3.0")

//Para corrutinas con alcance viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.0")


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}