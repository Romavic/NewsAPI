package ao.newsapi.romavicdosanjos.domain.mappers

import ao.newsapi.romavicdosanjos.data.dto.response.ArticleResponse
import ao.newsapi.romavicdosanjos.data.dto.response.NewsTopHeadlinesResponse
import ao.newsapi.romavicdosanjos.data.dto.response.SourceResponse
import ao.newsapi.romavicdosanjos.data.utilities.Mapper
import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.domain.entity.NewsTopHeadlinesEntity
import ao.newsapi.romavicdosanjos.domain.entity.SourceEntity

object NewsTopHeadlinesMapper : Mapper<NewsTopHeadlinesResponse, NewsTopHeadlinesEntity> {
    override fun map(data: NewsTopHeadlinesResponse): NewsTopHeadlinesEntity {
        val articleModels = data.articleResponses?.map(ArticleMapper::map)
        return NewsTopHeadlinesEntity(
            status = data.status,
            totalResults = data.totalResults,
            articleResponses = articleModels?.toMutableList()
        )
    }
}

object ArticleMapper : Mapper<ArticleResponse, ArticleEntity> {
    override fun map(data: ArticleResponse): ArticleEntity {
        return ArticleEntity(
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

object SourceMapper : Mapper<SourceResponse, SourceEntity> {
    override fun map(data: SourceResponse): SourceEntity {
        return SourceEntity(
            id = data.id,
            name = data.name
        )
    }
}
