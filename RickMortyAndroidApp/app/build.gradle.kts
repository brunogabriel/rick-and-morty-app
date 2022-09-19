plugins {
    id("com.spotify.ruler")
}

android {
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    // Modules
    Modules.getAll().forEach { module -> implementation(project(module)) }

    // Libraries
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.retrofit)
    dagger()
    room()

    // Tests
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.androidxJunit)
    androidTestImplementation(TestDependencies.espresso)
}

ruler {
    abi.set("arm64-v8a")
    locale.set("en")
    screenDensity.set(480)
    sdkVersion.set(27)

    ownershipFile.set(project.file("ownership.yaml"))
    defaultOwner.set("default-team")
}