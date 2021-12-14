android {
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    // Modules
    implementation(project(Modules.shared))
    implementation(project(Modules.dataLocal))
    implementation(project(Modules.network))
    implementation(project(Modules.styleGuide))

    // dependencies
    dagger()
    room()
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.fragmentKtx)
    implementation(Dependencies.material)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.kotlinxSerialization)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.navigationUiKtx)
    implementation(Dependencies.navigationFragmentKtx)

    // test dependencies
    testImplementation(TestDependencies.junit)
    androidTestImplementation(TestDependencies.androidxJunit)
    androidTestImplementation(TestDependencies.espresso)
}