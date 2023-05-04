package ao.newsapi.romavicdosanjos.stub

import ao.newsapi.romavicdosanjos.data.dto.response.ArticleResponse
import ao.newsapi.romavicdosanjos.data.dto.response.NewsTopHeadlinesResponse
import ao.newsapi.romavicdosanjos.data.dto.response.SourceResponse

fun newsTopHeadlinesResponseStub() = NewsTopHeadlinesResponse(
    status = "Ok",
    totalResults = 100,
    articleResponses = mutableListOf(articleResponseStub())
)

fun articleResponseStub() = ArticleResponse(
    sourceResponse = sourceResponseStub(),
    author = "Florida Today",
    title = "SpaceX launch: Live updates as Falcon Heavy...",
    description = "Liftoff of Falcon Heavy with the ViaSat-3...",
    url = "https://www.floridatoday.com/story/tech/science/...",
    urlToImage = "https://www.gannett-cdn.com/presto/2023/...",
    publishedAt = "2023-05-01T00:26:00Z",
    content = "Space is important to us and thats why..."
)

fun sourceResponseStub() = SourceResponse(
    id = null,
    name = "WPBF"
)

