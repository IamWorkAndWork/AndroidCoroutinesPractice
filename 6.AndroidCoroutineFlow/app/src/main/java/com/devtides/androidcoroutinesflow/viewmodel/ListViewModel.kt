package com.devtides.androidcoroutinesflow.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.devtides.androidcoroutinesflow.model.NewsRepository
import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

class ListViewModel : ViewModel() {

//    val newsArticles = NewsRepository().getNewsArticle().asLiveData()

    private val _newsArticles = MutableLiveData<NewsArticle>()

    val newsArticles: LiveData<NewsArticle> = _newsArticles

    init {
        fetchWithCollect()

//        fetchWithOnEach()
    }

    private fun fetchWithCollect() {
        viewModelScope.launch {
            NewsRepository().getNewsArticle()
                .collect { model ->
                    _newsArticles.postValue(model)
                }
        }
    }

    private fun fetchWithOnEach() {
        viewModelScope.launch {
            NewsRepository().getNewsArticle()
                .onEach { model ->
                    _newsArticles.postValue(model)
                }
                .launchIn(this)
        }
    }

}