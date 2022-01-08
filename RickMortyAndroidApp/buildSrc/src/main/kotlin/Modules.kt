object Modules {
    const val main = ":main"
    const val shared = ":shared"
    const val network = ":network"
    const val dataLocal = ":data-local"
    const val styleGuide = ":style-guide"
    const val onboard = ":onboard"
    const val deeplink = ":deeplink"
    const val testing = ":testing"

    fun getAll() = listOf(
        main,
        shared,
        network,
        dataLocal,
        styleGuide,
        onboard,
        deeplink,
        testing
    )
}