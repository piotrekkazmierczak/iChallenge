package com.kazman.impraisechallenge.activity.main

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.kazman.impraisechallenge.activity.ViewModel

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class MainFeedViewModel: BaseObservable(), ViewModel {

    val model = MainFeedModel().also { it.changeCallback = {
        bindingResourceId -> notifyPropertyChanged(bindingResourceId)
    } }

    override fun onDestroy() {
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    override fun onCreate() {
        model.loadUsers()
    }

    @Bindable
    fun getAdapter() = model.listAdapter


    fun reset() {
        model.listAdapter.clearItems()
        model.loadUsers()
    }
}
