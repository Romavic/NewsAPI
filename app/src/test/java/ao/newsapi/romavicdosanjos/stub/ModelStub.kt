package ao.newsapi.romavicdosanjos.stub

import ao.newsapi.romavicdosanjos.domain.model.ArticleModel
import ao.newsapi.romavicdosanjos.domain.model.NewsTopHeadlinesModel
import ao.newsapi.romavicdosanjos.domain.model.SourceModel

fun newsTopHeadlinesModelStub() = NewsTopHeadlinesModel(
    status = "Ok",
    totalResults = 100,
    articleResponses = mutableListOf(articleModelStub())
)

fun articleModelStub() = ArticleModel(
    sourceResponse = sourceModelStub(),
    author = "Florida Today",
    title = "SpaceX launch: Live updates as Falcon Heavy...",
    description = "Liftoff of Falcon Heavy with the ViaSat-3...",
    url = "https://www.floridatoday.com/story/tech/science/...",
    urlToImage = "https://www.gannett-cdn.com/presto/2023/...",
    publishedAt = "2023-05-01T00:26:00Z",
    content = "Space is important to us and thats why..."
)

fun sourceModelStub() = SourceModel(
    id = null,
    name = "WPBF"
)

