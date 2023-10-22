package ao.newsapi.romavicdosanjos.domain.repository

import ao.newsapi.romavicdosanjos.domain.entity.NewsTopHeadlinesEntity
import kotlinx.coroutines.flow.Flow

interface NewsTopHeadlinesRepository {

    suspend fun getNewsTopHeadlines(): Flow<NewsTopHeadlinesEntity>
}