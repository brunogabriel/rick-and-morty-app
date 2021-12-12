object Modules {
    const val main = ":main"
    const val shared = ":shared"
    const val network = ":network"

    fun getAll() = listOf(
        main,
        shared,
        network
    )
}