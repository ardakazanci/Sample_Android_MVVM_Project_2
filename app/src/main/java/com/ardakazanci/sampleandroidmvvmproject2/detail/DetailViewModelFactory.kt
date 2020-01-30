package com.ardakazanci.sampleandroidmvvmproject2.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel

class DetailViewModelFactory(
    private val todoPropery: TodoModel,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(todoPropery, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}