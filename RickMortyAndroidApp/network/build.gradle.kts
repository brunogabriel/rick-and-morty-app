android {
    defaultConfig {
        buildConfigField("String", "BASE_URL", "\"https://rickandmortyapi.com/api/\"")
    }
}

dependencies {
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.kotlinxSerialization)
    implementation(Dependencies.kotlinxSerializationConverter)
    dagger()
    chuck()
    okHttp()

    testImplementation(TestDependencies.junit)
}