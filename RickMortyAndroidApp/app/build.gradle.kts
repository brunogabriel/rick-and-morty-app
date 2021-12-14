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