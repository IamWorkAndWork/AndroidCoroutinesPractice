package com.devtides.androidcoroutinesflow.model

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import retrofit2.http.GET

interface NewsService {

    @GET("DevTides/NewsApi/master/news.json")
    suspend fun getNews(): List<NewsArticle>

}