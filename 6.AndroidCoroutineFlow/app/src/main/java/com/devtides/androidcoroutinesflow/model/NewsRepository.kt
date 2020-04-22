package com.devtides.androidcoroutinesflow.model

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsRepository {

    companion object {
        //        https://raw.githubusercontent.com/DevTides/NewsApi/master/news.json
        private const val BASE_URL = "https://raw.githubusercontent.com/"
        private const val NEWS_DELAY = 2000L
    }

    fun newsService(): NewsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsService::class.java)
    }

    fun getNewsArticle(): Flow<NewsArticle> {
        return flow {
            var newsSource = newsService().getNews()
            newsSource.forEach {
                emit(it)
                delay(NEWS_DELAY)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getNewsArticleAlternative(): Flow<NewsArticle> {
        return newsService().getNews()
            .asFlow()
            .flowOn(Dispatchers.IO)
    }


}