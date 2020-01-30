package com.ardakazanci.sampleandroidmvvmproject2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import com.ardakazanci.sampleandroidmvvmproject2.R
import com.ardakazanci.sampleandroidmvvmproject2.databinding.DetailFragmentBinding

class DetailFragment : Fragment() {


    private val viewModel: DetailViewModel by lazy {
        ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val application = requireNotNull(activity).application


        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )

        val todoPropery = DetailFragmentArgs.fromBundle(arguments!!).selectedTodoProperty

        // ViewModelFactory created
        val viewModelFactory = DetailViewModelFactory(todoPropery, application)

        //ViewModel created and Binding Connected
        binding.viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        return binding.root
    }


}
