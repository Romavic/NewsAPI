package ao.newsapi.romavicdosanjos.extensions

import androidx.appcompat.widget.AppCompatImageView
import ao.newsapi.romavicdosanjos.R
import coil.load

fun AppCompatImageView.loadImage(path: String) {
    load(path) {
        placeholder(R.drawable.place_holder_drawable)
        error(R.drawable.place_holder_drawable)
    }
}