package com.rmakiyama.recyclerviewplayground.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.core.ext.assistedViewModels
import com.rmakiyama.recyclerviewplayground.databinding.FragmentHomeBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    @Inject lateinit var factory: Provider<HomeViewModel>
    private val viewModel by assistedViewModels { factory.get() }
    private val adapter by lazy {
        UserListAdapter(
            viewModel::toggleFavorite,
            viewModel::changeUser,
            viewModel::changePhoto
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.dummies.adapter = adapter

        with(viewModel) {
            dummies.observe(viewLifecycleOwner, adapter::submitList)
        }

        viewModel.dummies.observe(viewLifecycleOwner, Observer { users ->
            adapter.submitList(users)
        })
    }
}
