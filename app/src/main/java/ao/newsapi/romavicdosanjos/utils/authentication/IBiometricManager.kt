package ao.newsapi.romavicdosanjos.utils.authentication

interface IBiometricManager {
    fun initializedBiometric(
        title: String,
        subtitle: String,
        description: String,
        cancel: String,
        biometricAuthenticationState: () -> Unit
    )
}