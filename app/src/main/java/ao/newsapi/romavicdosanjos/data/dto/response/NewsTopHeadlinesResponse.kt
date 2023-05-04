@file:Suppress("PLUGIN_IS_NOT_ENABLED")

package ao.newsapi.romavicdosanjos.data.dto.response

import com.google.gson.annotations.SerializedName

data class NewsTopHeadlinesResponse(
    @SerializedName("status")
    val status: String? = "",
    @SerializedName("totalResults")
    val totalResults: Int? = 0,
    @SerializedName("articles")
    val articleResponses: MutableList<ArticleResponse>? = mutableListOf()
)

data class ArticleResponse(
    @SerializedName("source")
    val sourceResponse: SourceResponse? = SourceResponse(),
    @SerializedName("author")
    val author: String? = "",
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("description")
    val description: String? = "",
    @SerializedName("url")
    val url: String? = "",
    @SerializedName("urlToImage")
    val urlToImage: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("content")
    val content: String? = ""
)

data class SourceResponse(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = ""
)