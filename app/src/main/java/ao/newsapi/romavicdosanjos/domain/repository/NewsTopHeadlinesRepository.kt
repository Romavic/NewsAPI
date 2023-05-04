package ao.newsapi.romavicdosanjos.domain.repository

import ao.newsapi.romavicdosanjos.domain.model.NewsTopHeadlinesModel
import kotlinx.coroutines.flow.Flow

interface NewsTopHeadlinesRepository {

    suspend fun getNewsTopHeadlines(): Flow<NewsTopHeadlinesModel>
}