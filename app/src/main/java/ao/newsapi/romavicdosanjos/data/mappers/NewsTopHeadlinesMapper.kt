package ao.newsapi.romavicdosanjos.data.mappers

import ao.newsapi.romavicdosanjos.data.dto.response.ArticleResponse
import ao.newsapi.romavicdosanjos.data.dto.response.NewsTopHeadlinesResponse
import ao.newsapi.romavicdosanjos.data.dto.response.SourceResponse
import ao.newsapi.romavicdosanjos.data.utilities.Mapper
import ao.newsapi.romavicdosanjos.domain.model.ArticleModel
import ao.newsapi.romavicdosanjos.domain.model.NewsTopHeadlinesModel
import ao.newsapi.romavicdosanjos.domain.model.SourceModel

object NewsTopHeadlinesMapper : Mapper<NewsTopHeadlinesResponse, NewsTopHeadlinesModel> {
    override fun map(data: NewsTopHeadlinesResponse): NewsTopHeadlinesModel {
        val articleModels = data.articleResponses?.map(ArticleMapper::map)
        return NewsTopHeadlinesModel(
            status = data.status,
            totalResults = data.totalResults,
            articleResponses = articleModels?.toMutableList()
        )
    }
}

object ArticleMapper : Mapper<ArticleResponse, ArticleModel> {
    override fun map(data: ArticleResponse): ArticleModel {
        return ArticleModel(
            sourceResponse = data.sourceResponse?.let { SourceMapper.map(it) },
            author = data.author,
            title = data.title,
            description = data.description,
            url = data.url,
            urlToImage = data.urlToImage,
            publishedAt = data.publishedAt,
            content = data.content
        )
    }
}

object SourceMapper : Mapper<SourceResponse, SourceModel> {
    override fun map(data: SourceResponse): SourceModel {
        return SourceModel(
            id = data.id,
            name = data.name
        )
    }
}
