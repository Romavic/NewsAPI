package ao.newsapi.romavicdosanjos.di

import androidx.appcompat.app.AppCompatActivity
import ao.newsapi.romavicdosanjos.BuildConfig
import ao.newsapi.romavicdosanjos.utils.authentication.BiometricManagerImpl
import ao.newsapi.romavicdosanjos.utils.authentication.IBiometricManager
import ao.newsapi.romavicdosanjos.domain.mappers.ArticleMapper
import ao.newsapi.romavicdosanjos.domain.mappers.NewsTopHeadlinesMapper
import ao.newsapi.romavicdosanjos.domain.mappers.SourceMapper
import ao.newsapi.romavicdosanjos.data.remotedatasource.NewsTopHeadlinesRemoteDataSource
import ao.newsapi.romavicdosanjos.data.repository.NewsTopHeadlinesRepositoryImpl
import ao.newsapi.romavicdosanjos.domain.repository.NewsTopHeadlinesRepository
import ao.newsapi.romavicdosanjos.domain.usecase.NewsTopHeadlinesUseCase
import ao.newsapi.romavicdosanjos.presentation.screens.newstopheadlines.NewsTopHeadlinesViewModel
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val original = chain.request()
                val originalHttpUrl = original.url
                val urlWithKey = originalHttpUrl.newBuilder()
                    .addQueryParameter("country", "us")
                    .addQueryParameter("apikey", BuildConfig.API_KEY)
                    .build()
                chain.proceed(original.newBuilder().url(urlWithKey).build())
            }
        }.build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single {
        get<Retrofit>().create(
            NewsTopHeadlinesRemoteDataSource::class.java
        )
    }

    factory {
        NewsTopHeadlinesMapper
    }

    factory {
        ArticleMapper
    }

    factory {
        SourceMapper
    }

    factory<NewsTopHeadlinesRepository> {
        NewsTopHeadlinesRepositoryImpl(
            newsTopHeadlinesRemoteDataSource = get(),
            mappers = get()
        )
    }

    factory {
        NewsTopHeadlinesUseCase(
            newsTopHeadlinesRepository = get()
        )
    }

    factory {
        Dispatchers.IO
    }

    single {
        AppCompatActivity()
    }

    single<IBiometricManager> { (activity: AppCompatActivity) ->
        BiometricManagerImpl(activity = activity)
    }

    viewModel {
        NewsTopHeadlinesViewModel(
            newsTopHeadlinesUseCase = get(),
            IO = get()
        )
    }
}
