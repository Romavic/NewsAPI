package ao.newsapi.romavicdosanjos.data.mappers

import ao.newsapi.romavicdosanjos.stub.articleResponseStub
import ao.newsapi.romavicdosanjos.stub.newsTopHeadlinesResponseStub
import ao.newsapi.romavicdosanjos.stub.sourceResponseStub
import org.junit.Assert.assertEquals
import org.junit.Test

class MappersTest {

    private val sourceResponse = sourceResponseStub()
    private val articleResponse = articleResponseStub()
    private val newsTopHeadlinesResponse = newsTopHeadlinesResponseStub()

    @Test
    fun `should map SourceResponse to SourceModel`() {
        val sourceModel = SourceMapper.map(sourceResponse)

        assertEquals(sourceResponse.id, sourceModel.id)
        assertEquals(sourceResponse.name, sourceModel.name)
    }

    @Test
    fun `should map ArticleResponse to ArticleModel`() {
        val articleModel = ArticleMapper.map(articleResponse)

        assertEquals(articleResponse.author, articleModel.author)
        assertEquals(articleResponse.title, articleModel.title)
        assertEquals(articleResponse.description, articleModel.description)
        assertEquals(articleResponse.url, articleModel.url)
        assertEquals(articleResponse.urlToImage, articleModel.urlToImage)
        assertEquals(articleResponse.publishedAt, articleModel.publishedAt)
        assertEquals(articleResponse.content, articleModel.content)

        val sourceModel = SourceMapper.map(articleResponse.sourceResponse!!)
        assertEquals(articleResponse.sourceResponse?.id, sourceModel.id)
        assertEquals(articleResponse.sourceResponse?.name, sourceModel.name)
    }

    @Test
    fun `should map NewsTopHeadlinesResponse to NewsTopHeadlinesModel`() {
        val newsTopHeadlinesModel = NewsTopHeadlinesMapper.map(newsTopHeadlinesResponse)

        assertEquals(newsTopHeadlinesResponse.status, newsTopHeadlinesModel.status)
        assertEquals(newsTopHeadlinesResponse.totalResults, newsTopHeadlinesModel.totalResults)

        val articleModels = newsTopHeadlinesResponse.articleResponses?.map { ArticleMapper.map(it) }
        assertEquals(articleModels, newsTopHeadlinesModel.articleResponses)
    }
}
