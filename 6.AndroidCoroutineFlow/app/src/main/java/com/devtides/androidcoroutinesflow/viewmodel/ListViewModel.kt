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

    val newsArticles = NewsRepository().getNewsArticle().asLiveData()

}