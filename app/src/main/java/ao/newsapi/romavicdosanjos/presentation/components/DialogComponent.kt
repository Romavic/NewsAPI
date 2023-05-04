package ao.newsapi.romavicdosanjos.presentation.components

import androidx.biometric.BiometricPrompt

fun biometricPromptDialog(
    title: CharSequence,
    subtitle: CharSequence,
    description: CharSequence,
    negativeText: CharSequence,
): BiometricPrompt.PromptInfo {
    return BiometricPrompt.PromptInfo.Builder()
        .setTitle(title)
        .setSubtitle(subtitle)
        .setDescription(description)
        .setNegativeButtonText(negativeText)
        .build()
}

