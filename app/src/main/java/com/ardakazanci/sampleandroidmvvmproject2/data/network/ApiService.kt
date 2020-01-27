package com.ardakazanci.sampleandroidmvvmproject2.data.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()


object TodoApi {

    val retrofitService: TodoApiService by lazy {
        retrofit.create(TodoApiService::class.java)
    }

}