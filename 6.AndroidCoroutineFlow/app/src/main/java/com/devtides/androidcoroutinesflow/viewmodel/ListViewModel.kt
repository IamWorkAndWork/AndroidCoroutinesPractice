package com.devtides.androidcoroutinesflow.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.devtides.androidcoroutinesflow.model.NewsRepository
import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ListViewModel : ViewModel() {

    //way1
//    val newsArticles = NewsRepository().getNewsArticle().asLiveData()

    var newsArticles:
            MutableLiveData<NewsArticle> =
        MutableLiveData()// = NewsRepository().getNewsArticleAlternative().asLiveData()

    var job: Job? = null

    init {
        //way2
//        onNewsArticle()

        //way3
        onNewsArticle2()
    }

//    fun onNewsArticle() = runBlocking {
//        newsArticles = NewsRepository()
//            .getNewsArticleAlternative()
//            .onEach {
//                delay(1000L)
//            }
//            .onStart {
//
//            }
//            .catch {
//
//            }
//            .asLiveData() as MutableLiveData<NewsArticle>
//    }

    fun onNewsArticle2() {
        runBlocking {
            job = NewsRepository()
                .getNewsArticleAlternative()
                .onEach { article ->
//                    delay(1000L)
                    Log.e("print", "data = " + article.title)
                    newsArticles.value = article
                }
                .launchIn(this)
        }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}