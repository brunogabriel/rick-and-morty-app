dependencies {
    // dependencies
    room()
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.coroutines)

    // test dependencies
    unitTest()
    instrumentalTest()
}