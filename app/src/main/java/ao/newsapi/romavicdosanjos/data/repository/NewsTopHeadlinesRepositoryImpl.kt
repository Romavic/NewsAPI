package ao.newsapi.romavicdosanjos.data.repository

import ao.newsapi.romavicdosanjos.data.mappers.NewsTopHeadlinesMapper
import ao.newsapi.romavicdosanjos.data.remotedatasource.NewsTopHeadlinesRemoteDataSource
import ao.newsapi.romavicdosanjos.domain.model.NewsTopHeadlinesModel
import ao.newsapi.romavicdosanjos.domain.repository.NewsTopHeadlinesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsTopHeadlinesRepositoryImpl(
    private val newsTopHeadlinesRemoteDataSource: NewsTopHeadlinesRemoteDataSource,
    private val mappers: NewsTopHeadlinesMapper
) : NewsTopHeadlinesRepository {

    override suspend fun getNewsTopHeadlines(): Flow<NewsTopHeadlinesModel> {
        return flow {
            emit(mappers.map(newsTopHeadlinesRemoteDataSource.getNewsTopHeadlines()))
        }
    }
}