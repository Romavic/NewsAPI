package ao.newsapi.romavicdosanjos.domain.usecase

import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.domain.repository.NewsTopHeadlinesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NewsTopHeadlinesUseCase(
    private val newsTopHeadlinesRepository: NewsTopHeadlinesRepository
) {

    suspend fun articles(): Flow<MutableList<ArticleEntity>?> {
        return newsTopHeadlinesRepository.getNewsTopHeadlines().map { newsTopHeadlinesModel ->
            newsTopHeadlinesModel.articleResponses
        }
    }
}