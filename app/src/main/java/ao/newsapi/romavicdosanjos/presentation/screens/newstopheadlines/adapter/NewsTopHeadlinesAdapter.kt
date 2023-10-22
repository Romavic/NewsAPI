package ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ao.newsapi.romavicdosanjos.databinding.ArticleItemBinding
import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.extensions.loadImage

class NewsTopHeadlinesAdapter(
    private var onTitleSetOnClick: OnTitleSetOnClick
) : ListAdapter<ArticleEntity, NewsTopHeadlinesAdapter.NewsTopHeadlinesHolder>(
    NewsTopHeadlinesDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsTopHeadlinesHolder {
        return NewsTopHeadlinesHolder(
            ArticleItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsTopHeadlinesHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class NewsTopHeadlinesHolder(
        private val binding: ArticleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleEntity) {
            with(binding) {
                imageItemArticle.loadImage(article.urlToImage.toString())
                with(titleItemArticle) {
                    text = article.title
                    setOnClickListener {
                        onTitleSetOnClick.listener(article)
                    }
                }
            }
        }
    }

    private class NewsTopHeadlinesDiffUtil : DiffUtil.ItemCallback<ArticleEntity>() {
        override fun areItemsTheSame(
            oldItem: ArticleEntity,
            newItem: ArticleEntity
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: ArticleEntity,
            newItem: ArticleEntity
        ): Boolean = oldItem == newItem
    }

    interface OnTitleSetOnClick {
        fun listener(article: ArticleEntity)
    }
}
