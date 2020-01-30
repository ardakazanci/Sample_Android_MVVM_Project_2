package com.ardakazanci.sampleandroidmvvmproject2.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ardakazanci.sampleandroidmvvmproject2.data.TodoModel
import com.ardakazanci.sampleandroidmvvmproject2.databinding.ItemListTodoBinding

class TodoAdapter(private val onClickListener: OnClickListener) : ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        return TodoViewHolder(ItemListTodoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        val todoProperty = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(todoProperty)
        }
        holder.bind(todoProperty)
    }

    class TodoViewHolder(private var binding: ItemListTodoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(todoProperty: TodoModel) {
            binding.todoProperty = todoProperty
            binding.executePendingBindings()
        }

    }


    companion object DiffCallback : DiffUtil.ItemCallback<TodoModel>() {

        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem._id == newItem._id
        }

    }

    class OnClickListener(val clickListener: (todoProperty: TodoModel) -> Unit) {
        fun onClick(todoProperty: TodoModel) = clickListener(todoProperty)
    }


}