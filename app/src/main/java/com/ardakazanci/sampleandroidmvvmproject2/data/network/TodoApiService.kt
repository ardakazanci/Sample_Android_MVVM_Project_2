package com.ardakazanci.sampleandroidmvvmproject2.data.network

import retrofit2.Call
import retrofit2.http.GET

interface TodoApiService {

    @GET("todos")
    fun getTodos(): Call<String>

}