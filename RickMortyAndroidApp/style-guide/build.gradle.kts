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

    testImplementation(TestDependencies.junit)
}