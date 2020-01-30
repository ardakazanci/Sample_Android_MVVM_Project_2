package com.ardakazanci.sampleandroidmvvmproject2.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ardakazanci.sampleandroidmvvmproject2.R

import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import com.ardakazanci.sampleandroidmvvmproject2.home.TodoAdapter
import com.ardakazanci.sampleandroidmvvmproject2.home.TodoApiStatus

@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<TodoModel>?
) {

    val adapter = recyclerView.adapter as TodoAdapter
    adapter.submitList(data)

}

@BindingAdapter("apiStatus")
fun bindStatus(view: ImageView, status: TodoApiStatus?) {
    when (status) {
        TodoApiStatus.LOADING -> {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.loading_animation)
        }
        TodoApiStatus.ERROR -> {
            view.visibility = View.VISIBLE
            view.setImageResource(R.drawable.ic_connection_error)
        }
        TodoApiStatus.DONE -> {
            view.visibility = View.GONE

        }
    }
}