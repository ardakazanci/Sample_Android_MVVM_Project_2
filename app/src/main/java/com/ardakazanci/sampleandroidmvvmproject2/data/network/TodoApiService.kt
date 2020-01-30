package com.ardakazanci.sampleandroidmvvmproject2.data.network

import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


enum class TodoApiFilter(val value: String) {

    SHOW_COMPLETED("true"),
    SHOW_NOT_COMPLETED("false"),
    SHOW_ALL("all")

}


interface TodoApiService {

    /**
     * Retrofit bir çok thread yönetimini arka planda yaptığı için Call kısmını değiştirmemiz yeterli.
     * Coroutines Deferred await isimli bir yöntem içerir.
     * Değer hazır olana kadar engellemeden beklemesine neden olur ve ardından bu değer döndürülür.
     */
    @GET("todos")
    fun getTodos(): Deferred<List<TodoModel>>

    @GET("todos")
    fun getTodosFilter(@Query("completed") isCompleted: String): Deferred<List<TodoModel>>

}