package ao.newsapi.romavicdosanjos.data.utilities

interface Mapper<S, T> {
    fun map(data: S): T
}