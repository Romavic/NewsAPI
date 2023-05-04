package ao.newsapi.romavicdosanjos.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.htmToString(): Spanned {
    return HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)
}
