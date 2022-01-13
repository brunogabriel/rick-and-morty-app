dependencies {
    implementation(project(Modules.testing))
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.kotlinxSerialization)

    dagger()
    room()

    unitTest()
    instrumentalTest()
}