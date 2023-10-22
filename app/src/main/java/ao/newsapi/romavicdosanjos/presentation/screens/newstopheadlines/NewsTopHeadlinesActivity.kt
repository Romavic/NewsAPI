package ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ao.newsapi.romavicdosanjos.R
import ao.newsapi.romavicdosanjos.utils.authentication.IBiometricManager
import ao.newsapi.romavicdosanjos.data.utilities.ResultData
import ao.newsapi.romavicdosanjos.databinding.ActivityMainBinding
import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.extensions.gone
import ao.newsapi.romavicdosanjos.extensions.visible
import ao.newsapi.romavicdosanjos.presentation.screens.articledetails.ArticleDetailsActivity
import ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines.adapter.NewsTopHeadlinesAdapter
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class NewsTopHeadlinesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var newsTopHeadlinesAdapter: NewsTopHeadlinesAdapter
    private val viewModel: NewsTopHeadlinesViewModel by inject()
    private val iBiometricManager: IBiometricManager by inject {
        parametersOf(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        iBiometricManager.initializedBiometric(
            getString(R.string.title_biometric),
            getString(R.string.subtitle_biometric),
            getString(R.string.description_biometric),
            getString(R.string.cancel_button)
        ) {
            configureAdapter()
            newsTopHeadlinesRequest()
        }
    }

    private fun configureAdapter() {
        newsTopHeadlinesAdapter = NewsTopHeadlinesAdapter(
            object : NewsTopHeadlinesAdapter.OnTitleSetOnClick {
                override fun listener(article: ArticleEntity) {
                    startActivity(Intent(
                        this@NewsTopHeadlinesActivity,
                        ArticleDetailsActivity::class.java
                    ).apply {
                        putExtra("article", article)
                    })
                }
            }
        )
        with(binding.recyclerView) {
            layoutManager = LinearLayoutManager(
                this@NewsTopHeadlinesActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = newsTopHeadlinesAdapter
        }
    }

    private fun newsTopHeadlinesRequest() {
        viewModel.articlesLiveData.observe(this) { result ->
            when (result) {
                is ResultData.Success -> {
                    with(binding) {
                        progressBar.gone()
                        dataLayout.visible()
                        errorConnection.root.gone()
                    }.apply {
                        newsTopHeadlinesAdapter.submitList(result.data)
                    }
                }

                is ResultData.Failure -> {
                    with(binding) {
                        dataLayout.gone()
                        progressBar.gone()
                        with(errorConnection) {
                            root.visible()
                            textError.text = result.error.message
                            refreshButton.setOnClickListener {
                                viewModel.requestArticlesSortedByDate()
                            }
                        }
                    }
                }

                is ResultData.Loading -> {
                    with(binding) {
                        dataLayout.visible()
                        progressBar.visible()
                        errorConnection.root.gone()
                    }
                }
            }
        }
    }
}