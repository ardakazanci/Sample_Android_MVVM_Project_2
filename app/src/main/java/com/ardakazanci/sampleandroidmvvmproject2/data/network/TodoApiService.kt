package com.ardakazanci.sampleandroidmvvmproject2.data.network

import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import retrofit2.Call
import retrofit2.http.GET

interface TodoApiService {

    @GET("todos")
    fun getTodos(): Call<List<TodoModel>>

}