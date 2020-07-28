package com.devtides.androidcoroutinesflow.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.devtides.androidcoroutinesflow.R
import com.devtides.androidcoroutinesflow.viewmodel.ListViewModel
import com.devtides.coroutinesretrofit.view.NewsListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<ListViewModel>()
    private val newsListAdapter = NewsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsList.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = newsListAdapter
        }

        observeViewModel()
        onListener()
    }

    private fun onListener() {
        newsListAdapter?.onClickItem = { article ->
            Toast.makeText(this, article.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.newsArticles.observe(this, Observer { article ->
            article?.let {
                loading_view.visibility = View.GONE
                newsList.visibility = View.VISIBLE
                newsListAdapter.onAddNewsItem(article)
                newsList.smoothScrollToPosition(0)
            }
        })
    }
}
