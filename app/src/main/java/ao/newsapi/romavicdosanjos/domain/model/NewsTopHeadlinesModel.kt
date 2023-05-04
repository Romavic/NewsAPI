package ao.newsapi.romavicdosanjos.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsTopHeadlinesModel(
    var status: String? = "",
    var totalResults: Int? = 0,
    var articleResponses: MutableList<ArticleModel>? = mutableListOf()
) : Parcelable

@Parcelize
data class ArticleModel(
    var sourceResponse: SourceModel? = SourceModel(),
    var author: String? = "",
    var title: String? = "",
    var description: String? = "",
    var url: String? = "",
    var urlToImage: String? = "",
    var publishedAt: String? = "",
    var content: String? = ""
) : Parcelable

@Parcelize
data class SourceModel(
    var id: String? = "",
    var name: String? = "",
) : Parcelable