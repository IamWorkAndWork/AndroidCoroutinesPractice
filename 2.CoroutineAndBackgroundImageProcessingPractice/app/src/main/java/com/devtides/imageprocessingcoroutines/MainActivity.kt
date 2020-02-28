package com.devtides.imageprocessingcoroutines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val IMAGE_URL =
        "https://raw.githubusercontent.com/DevTides/JetpackDogsApp/master/app/src/main/res/drawable/dog.png"

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        coroutineScope.launch {
//            delay(2000L)
            val originalDeferred = coroutineScope.async(Dispatchers.IO) {
                getOriginalBitmap()
            }

            val originalBitmap = originalDeferred.await()

            val filteredDeferred =
                coroutineScope.async(Dispatchers.Default) { applyFilter(originalBitmap) }

            val filterBitmap = filteredDeferred.await()

            loadImage(filterBitmap)
        }
    }

    private fun applyFilter(originalBitmap: Bitmap): Bitmap {
        return Filter.apply(originalBitmap)
    }


    private fun getOriginalBitmap(): Bitmap {
        return URL(IMAGE_URL).openStream().use {
            BitmapFactory.decodeStream(it)
        }
    }


    private fun loadImage(bmp: Bitmap) {
        progressBar.visibility = View.GONE
        imageView.setImageBitmap(bmp)
        imageView.visibility = View.VISIBLE
    }

}
