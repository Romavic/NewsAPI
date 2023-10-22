package ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ao.newsapi.romavicdosanjos.data.utilities.ResultData
import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.domain.usecase.NewsTopHeadlinesUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class NewsTopHeadlinesViewModel(
    private val newsTopHeadlinesUseCase: NewsTopHeadlinesUseCase,
    private val IO: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val articlesMutableLiveData = MutableLiveData<ResultData<MutableList<ArticleEntity>?>>()
    val articlesLiveData: LiveData<ResultData<MutableList<ArticleEntity>?>>
        get() = articlesMutableLiveData

    init {
        requestArticlesSortedByDate()
    }

    fun requestArticlesSortedByDate() {
        viewModelScope.launch {
            newsTopHeadlinesUseCase.articles()
                .flowOn(IO)
                .onStart {
                    articlesMutableLiveData.postValue(ResultData.Loading)
                }
                .catch { throwable ->
                    articlesMutableLiveData.postValue(ResultData.Failure(throwable))
                }
                .collect { newsTopHeadlinesModel ->
                    newsTopHeadlinesModel?.sortBy {
                        it.publishedAt
                    }.apply {
                        articlesMutableLiveData.value =
                            ResultData.Success(data = newsTopHeadlinesModel)
                    }
                }
        }
    }
}