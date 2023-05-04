package ao.newsapi.romavicdosanjos.data.utilities

sealed class ResultData<out T> {
    data class Success<out T>(val data: T) : ResultData<T>()
    data class Failure(val error: Throwable) : ResultData<Nothing>()
    object Loading : ResultData<Nothing>()
}

