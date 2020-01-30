package com.ardakazanci.sampleandroidmvvmproject2.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration

import com.ardakazanci.sampleandroidmvvmproject2.R
import com.ardakazanci.sampleandroidmvvmproject2.databinding.TodoFragmentBinding

class TodoFragment : Fragment() {


    private val viewModel: TodoViewModel by lazy {
        ViewModelProviders.of(this).get(TodoViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val binding = DataBindingUtil.inflate<TodoFragmentBinding>(
            inflater,
            R.layout.todo_fragment,
            container,
            false
        )


        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.todoItem.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
        binding.todoItem.adapter = TodoAdapter()



        return binding.root


    }


}
