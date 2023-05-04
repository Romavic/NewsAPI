package ao.newsapi.romavicdosanjos

import android.app.Application
import ao.newsapi.romavicdosanjos.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NewsApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NewsApiApplication)
            modules(appModule)
        }
    }
}