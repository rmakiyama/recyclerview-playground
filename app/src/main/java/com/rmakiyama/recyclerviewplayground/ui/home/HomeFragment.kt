package com.rmakiyama.recyclerviewplayground.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.observe
import com.rmakiyama.recyclerviewplayground.R
import com.rmakiyama.recyclerviewplayground.core.ext.assistedViewModels
import com.rmakiyama.recyclerviewplayground.databinding.FragmentHomeBinding
import com.rmakiyama.recyclerviewplayground.ui.home.item.DummyItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.android.support.DaggerFragment
import javax.inject.Inject
import javax.inject.Provider

class HomeFragment : DaggerFragment(R.layout.fragment_home) {

    @Inject lateinit var factory: Provider<HomeViewModel>
    private val viewModel by assistedViewModels { factory.get() }
    private val adapter = GroupAdapter<GroupieViewHolder>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)
        binding.dummies.adapter = adapter

        with(viewModel) {
            dummies.observe(viewLifecycleOwner) { dummies ->
                adapter.update(dummies.map(::DummyItem))
            }
        }
    }
}
