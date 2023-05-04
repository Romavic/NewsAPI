package ao.newsapi.romavicdosanjos.data.authentication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import ao.newsapi.romavicdosanjos.presentation.components.biometricPromptDialog

class BiometricManagerImpl(
    private val activity: AppCompatActivity
) : IBiometricManager {

    private val executor = ContextCompat.getMainExecutor(activity)

    override fun initializedBiometric(
        title: String,
        subtitle: String,
        description: String,
        cancel: String,
        biometricAuthenticationState: () -> Unit
    ) {
        when {
            isBiometricReady(activity) -> {
                activity.apply {
                    BiometricPrompt(
                        this, executor, object : BiometricPrompt.AuthenticationCallback() {
                            override fun onAuthenticationError(
                                errorCode: Int,
                                errString: CharSequence
                            ) {
                                super.onAuthenticationError(errorCode, errString)
                                biometricAuthenticationState()
                            }

                            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                                super.onAuthenticationSucceeded(result)
                                biometricAuthenticationState()
                            }

                            override fun onAuthenticationFailed() {
                                super.onAuthenticationFailed()
                                biometricAuthenticationState()
                            }
                        }
                    ).authenticate(
                        biometricPromptDialog(title, subtitle, description, cancel)
                    )
                }
            }

            else -> biometricAuthenticationState()
        }
    }

    private fun isBiometricReady(context: Context): Boolean {
        return hasBiometricCapability(context) == BiometricManager.BIOMETRIC_SUCCESS
    }

    private fun hasBiometricCapability(context: Context): Int {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate()
    }
}