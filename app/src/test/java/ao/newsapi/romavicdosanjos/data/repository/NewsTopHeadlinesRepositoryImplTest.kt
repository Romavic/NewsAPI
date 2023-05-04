package ao.newsapi.romavicdosanjos.data.repository

import ao.newsapi.romavicdosanjos.data.mappers.NewsTopHeadlinesMapper
import ao.newsapi.romavicdosanjos.data.remotedatasource.NewsTopHeadlinesRemoteDataSource
import ao.newsapi.romavicdosanjos.domain.repository.NewsTopHeadlinesRepository
import ao.newsapi.romavicdosanjos.stub.newsTopHeadlinesResponseStub
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class NewsTopHeadlinesRepositoryImplTest {

    private val newsTopHeadlinesRemoteDataSource = mockk<NewsTopHeadlinesRemoteDataSource>()
    private val mapper = NewsTopHeadlinesMapper

    private lateinit var repository: NewsTopHeadlinesRepository

    @Before
    fun setup() {
        repository = NewsTopHeadlinesRepositoryImpl(newsTopHeadlinesRemoteDataSource, mapper)
    }

    @Test
    fun `getNewsTopHeadlines() should returns expected model`() = runBlocking {
        // Given
        val response = newsTopHeadlinesResponseStub()
        coEvery { newsTopHeadlinesRemoteDataSource.getNewsTopHeadlines() } returns response

        // When
        val flow = repository.getNewsTopHeadlines()

        // Then
        flow.collect {
            assertEquals(mapper.map(response), it)
        }
    }


    @Test
    fun `getNewsTopHeadlines() should returns unexpected error`() = runBlocking {
        // Given
        val expectedError = Throwable("unexpected error")
        coEvery {
            newsTopHeadlinesRemoteDataSource.getNewsTopHeadlines()
        } throws expectedError

        // When
        val result = repository.getNewsTopHeadlines()

        // Then
        result.test {
            assertEquals(
                expected = awaitError(),
                actual = expectedError,
            )
        }
    }

}
