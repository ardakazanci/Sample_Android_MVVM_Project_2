package com.ardakazanci.sampleandroidmvvmproject2.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApi
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TodoViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String>
        get() = _response


    init {

        getTodoProperties()

    }

    private fun getTodoProperties() {

        TodoApi.retrofitService.getTodos().enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Error : " + t.printStackTrace()
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = "Result : " + response.body()
            }


        })

    }

}
