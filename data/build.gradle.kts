plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.room)
    alias(libs.plugins.jetbrains.kotlin.serialization)
}

android {
    namespace = "com.example.data"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        buildConfig = true
    }
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":domain"))

    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    ksp(libs.room.compiler)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.okhttp)
    implementation(libs.okhttp.logging.interceptor)
    implementation(platform(libs.okhttp.bom))

    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.converter.scalars)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit)

    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.timber)

    coreLibraryDesugaring(libs.android.desugarJdkLibs)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
}