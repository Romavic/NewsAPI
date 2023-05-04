package ao.newsapi.romavicdosanjos.data.authentication

interface IBiometricManager {
    fun initializedBiometric(
        title: String,
        subtitle: String,
        description: String,
        cancel: String,
        biometricAuthenticationState: () -> Unit
    )
}