package ao.newsapi.romavicdosanjos.data.remotedatasource

import ao.newsapi.romavicdosanjos.data.dto.response.NewsTopHeadlinesResponse
import retrofit2.http.GET

interface NewsTopHeadlinesRemoteDataSource {

    @GET("top-headlines")
    suspend fun getNewsTopHeadlines(): NewsTopHeadlinesResponse
}