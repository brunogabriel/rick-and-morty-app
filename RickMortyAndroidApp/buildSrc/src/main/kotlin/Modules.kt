object Modules {
    const val main = ":main"
    const val shared = ":shared"
    const val network = ":network"
    const val dataLocal = ":data-local"

    fun getAll() = listOf(
        main,
        shared,
        network,
        dataLocal
    )
}