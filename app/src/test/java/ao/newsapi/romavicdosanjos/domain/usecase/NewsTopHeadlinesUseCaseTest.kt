package ao.newsapi.romavicdosanjos.domain.usecase

import ao.newsapi.romavicdosanjos.domain.repository.NewsTopHeadlinesRepository
import ao.newsapi.romavicdosanjos.stub.newsTopHeadlinesModelStub
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class NewsTopHeadlinesUseCaseTest {

    private val repositoryMock = mockk<NewsTopHeadlinesRepository>(relaxed = true)
    private lateinit var useCase: NewsTopHeadlinesUseCase

    @Before
    fun setup() {
        useCase = NewsTopHeadlinesUseCase(newsTopHeadlinesRepository = repositoryMock)
    }

    @Test
    fun `articles() returns expected model`() = runBlocking {
        // Given
        val expected = newsTopHeadlinesModelStub()

        coEvery {
            repositoryMock.getNewsTopHeadlines()
        } returns flow {
            emit(expected)
        }

        // When
        val result = useCase.articles()

        // Then
        result.test {
            assertEquals(
                expected = awaitItem(),
                actual = expected.articleResponses
            )
            awaitComplete()
        }
    }


    @Test
    fun `articles() returns unexpected error`() = runBlocking {
        val errorMessage = Throwable("Error retrieving news top headlines")

        coEvery {
            repositoryMock.getNewsTopHeadlines()
        } returns flow {
            throw errorMessage
        }

        // When
        val result = useCase.articles()

        // Then
        result.test {
            assertEquals(
                expected = awaitError().message,
                actual = errorMessage.message
            )
        }
    }
}