android {
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(Modules.shared))

    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.picasso)
    implementation(Dependencies.shimmer)
    implementation(Dependencies.lottie)

    testImplementation(TestDependencies.junit)
}