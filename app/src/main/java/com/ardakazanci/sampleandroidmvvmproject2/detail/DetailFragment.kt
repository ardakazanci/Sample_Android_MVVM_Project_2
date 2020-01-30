package com.ardakazanci.sampleandroidmvvmproject2.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

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
        val binding = DataBindingUtil.inflate<DetailFragmentBinding>(
            inflater,
            R.layout.detail_fragment,
            container,
            false
        )

        return binding.root
    }


}
