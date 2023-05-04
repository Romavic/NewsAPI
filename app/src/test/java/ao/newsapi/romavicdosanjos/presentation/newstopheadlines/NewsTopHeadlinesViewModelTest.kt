package ao.newsapi.romavicdosanjos.presentation.newstopheadlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import ao.newsapi.romavicdosanjos.MainCoroutineRule
import ao.newsapi.romavicdosanjos.data.utilities.ResultData
import ao.newsapi.romavicdosanjos.domain.usecase.NewsTopHeadlinesUseCase
import ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines.NewsTopHeadlinesViewModel
import ao.newsapi.romavicdosanjos.stub.articleModelStub
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class NewsTopHeadlinesViewModelTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NewsTopHeadlinesViewModel
    private val newsTopHeadlinesUseCase = mockk<NewsTopHeadlinesUseCase>(relaxed = true)

    @Before
    fun setUp() {
        viewModel = NewsTopHeadlinesViewModel(
            newsTopHeadlinesUseCase, StandardTestDispatcher(),
        )
    }

    @Test
    fun `requestArticlesSortedByDate() should update articlesLiveData with ResultDataSuccess`() =
        runTest {
            // Given
            val listArticles = mutableListOf(articleModelStub())

            coEvery {
                newsTopHeadlinesUseCase.articles()
            } returns flow {
                emit(listArticles)
            }

            // When
            viewModel.requestArticlesSortedByDate()

            // Then
            assertEquals(ResultData.Success(listArticles), viewModel.articlesLiveData.value)
        }

    @Test
    fun `requestArticlesSortedByDate() should update articlesLiveData with ResultDataLoading`() =
        runBlocking {
            // Given
            coEvery {
                newsTopHeadlinesUseCase.articles()
            } returns flowOf()

            // When
            viewModel.requestArticlesSortedByDate()

            // Then
            assertEquals(ResultData.Loading, viewModel.articlesLiveData.value)
        }

    @Test
    fun `requestArticlesSortedByDate should update articlesLiveData with failure result when use case throws an exception`() =
        runBlocking {
            // Given
            val exception = Throwable("Something went wrong")
            coEvery {
                newsTopHeadlinesUseCase.articles()
            } returns flow {
                throw exception
            }

            // When
            viewModel.requestArticlesSortedByDate()

            // Then
            assertEquals(ResultData.Failure(exception), viewModel.articlesLiveData.value)
        }
}
