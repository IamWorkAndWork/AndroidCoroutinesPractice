package com.devtides.androidcoroutinesflow.viewmodel

import android.os.Looper
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class SingleLiveEvent<T> : MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        // Observe the internal MutableLiveData
        super.observe(owner, Observer { t ->
            if (mPending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }


    override fun setValue(t: T?) {
        mPending.set(true)

        if(Looper.myLooper() == Looper.getMainLooper()) {
            super.setValue(t)
        } else {
            super.postValue(t)
        }
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    fun call() {
        if(Looper.myLooper() == Looper.getMainLooper()) {
            value = null
        } else {
            postValue(null)
        }
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}