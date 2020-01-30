package com.ardakazanci.sampleandroidmvvmproject2.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration

import com.ardakazanci.sampleandroidmvvmproject2.R
import com.ardakazanci.sampleandroidmvvmproject2.data.network.TodoApiFilter
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

        // Menu Visiblity
        setHasOptionsMenu(true)
        return binding.root
        

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_todo, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        viewModel.updateListFilter(
            when (item.itemId) {
                R.id.show_completed -> TodoApiFilter.SHOW_COMPLETED
                R.id.show_not_completed -> TodoApiFilter.SHOW_NOT_COMPLETED
                else -> TodoApiFilter.SHOW_ALL
            }
        )


        return true
    }


}
