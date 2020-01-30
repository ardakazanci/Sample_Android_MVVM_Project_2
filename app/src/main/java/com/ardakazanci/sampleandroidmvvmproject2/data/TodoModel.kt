package com.ardakazanci.sampleandroidmvvmproject2.data

import com.squareup.moshi.Json

data class TodoModel(
    val userId: Long,
    @Json(name = "id")
    val _id: Long,
    val title: String,
    val completed: Boolean
) {

    val isCompleted
        get() = completed

}