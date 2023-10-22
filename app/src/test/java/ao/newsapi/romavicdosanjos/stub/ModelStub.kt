package ao.newsapi.romavicdosanjos.stub

import ao.newsapi.romavicdosanjos.domain.entity.ArticleEntity
import ao.newsapi.romavicdosanjos.domain.entity.NewsTopHeadlinesEntity
import ao.newsapi.romavicdosanjos.domain.entity.SourceEntity

fun newsTopHeadlinesModelStub() = NewsTopHeadlinesEntity(
    status = "Ok",
    totalResults = 100,
    articleResponses = mutableListOf(articleModelStub())
)

fun articleModelStub() = ArticleEntity(
    sourceResponse = sourceModelStub(),
    author = "Florida Today",
    title = "SpaceX launch: Live updates as Falcon Heavy...",
    description = "Liftoff of Falcon Heavy with the ViaSat-3...",
    url = "https://www.floridatoday.com/story/tech/science/...",
    urlToImage = "https://www.gannett-cdn.com/presto/2023/...",
    publishedAt = "2023-05-01T00:26:00Z",
    content = "Space is important to us and thats why..."
)

fun sourceModelStub() = SourceEntity(
    id = null,
    name = "WPBF"
)

