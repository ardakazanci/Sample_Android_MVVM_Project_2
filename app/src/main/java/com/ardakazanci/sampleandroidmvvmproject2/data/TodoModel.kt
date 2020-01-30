package com.ardakazanci.sampleandroidmvvmproject2.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TodoModel(
    val userId: Long,
    @Json(name = "id")
    val _id: Long,
    val title: String,
    val completed: Boolean
) : Parcelable {

    val isCompleted
        get() = completed

}