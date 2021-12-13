plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdk = AppConfig.compileSdk

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

    // TODO: aplicar em subprojects???
    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
        java.srcDirs("src/main/kotlin")
    }
    sourceSets.getByName("test") {
        java.srcDir("src/test/kotlin")
        java.srcDir("src/test/kotlin")
    }
}

dependencies {
    implementation(project(Modules.shared))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.picasso)

    testImplementation(TestDependencies.junit)
}