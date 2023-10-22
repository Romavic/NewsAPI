package ao.newsapi.romavicdosanjos.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsTopHeadlinesEntity(
    var status: String? = "",
    var totalResults: Int? = 0,
    var articleResponses: MutableList<ArticleEntity>? = mutableListOf()
) : Parcelable

@Parcelize
data class ArticleEntity(
    var sourceResponse: SourceEntity? = SourceEntity(),
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
) : Parcelable

@Parcelize
data class SourceEntity(
    var id: String? = "",
    var name: String? = "",
) : Parcelable