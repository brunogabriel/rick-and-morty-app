android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.shared))
    implementation(project(Modules.dataLocal))
    implementation(project(Modules.styleGuide))

    // dependencies
    dagger()
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.dynamicAnimation)
    implementation(Dependencies.material)
    implementation(Dependencies.kotlinxSerialization)

    // test dependencies
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.androidxJunit)
    androidTestImplementation(TestDependencies.espresso)
}