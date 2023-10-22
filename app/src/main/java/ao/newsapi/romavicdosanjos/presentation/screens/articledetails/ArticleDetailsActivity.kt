package ao.newsapi.romavicdosanjos.presentation.screens.articledetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ao.newsapi.romavicdosanjos.R
import ao.newsapi.romavicdosanjos.databinding.ArticleDetailsActivityBinding
import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.extensions.htmToString
import ao.newsapi.romavicdosanjos.extensions.loadImage

class ArticleDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ArticleDetailsActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ArticleDetailsActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val article = intent.getParcelableExtra<ArticleEntity>("article")

        with(binding) {
            with(imageNewsTopHeadlinesDetails) {
                isVisible = !article?.urlToImage.isNullOrEmpty()
                loadImage(article?.urlToImage.toString())
            }

            with(titleNewsTopHeadlinesDetails) {
                isVisible = !article?.title.isNullOrEmpty()
                text = getString(
                    R.string.article_text,
                    article?.title
                ).htmToString()
            }

            with(descriptionNewsTopHeadlinesDetails) {
                isVisible = !article?.description.isNullOrEmpty()
                text = getString(
                    R.string.description_text,
                    article?.description
                ).htmToString()
            }

            with(contentNewsTopHeadlinesDetails) {
                isVisible = !article?.content.isNullOrEmpty()
                text = getString(
                    R.string.content_text,
                    article?.content
                ).htmToString()
            }
        }
    }
}