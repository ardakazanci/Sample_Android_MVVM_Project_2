package com.ardakazanci.sampleandroidmvvmproject2.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel

class DetailViewModel(todoModel: TodoModel, app: Application) : AndroidViewModel(app) {

    private val _selectedTodoPropery = MutableLiveData<TodoModel>()
    val selectedTodoPropery: LiveData<TodoModel>
        get() = _selectedTodoPropery

    init {

        _selectedTodoPropery.value = todoModel

    }









}
