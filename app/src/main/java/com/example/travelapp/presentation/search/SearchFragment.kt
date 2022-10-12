package com.example.travelapp.presentation.search

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentSearchBinding
import com.example.travelapp.presentation.core.BaseFragment
import com.example.travelapp.presentation.util.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchViewModel>() {
    private lateinit var searchTopAdapter: SearchTopAdapter
    private lateinit var searchNearbyAdapter: SearchNearbyAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_search

    override fun getViewModelClass(): Class<SearchViewModel> = SearchViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        initAdapter()
        viewModel.apply {
            getTravelsByCategoryNearby()
            getTravelsByCategoryTopDestination()
        }
        initOnSearchNearbyItemSelectedListener()
        initOnSearchTopItemSelectedListener()
    }

    private fun initObservers() {
        observe(viewModel.topDestinationData) {
            searchTopAdapter.updateList(it)

        }
        observe(viewModel.nearbyData) {
            searchNearbyAdapter.updateList(it)
        }
    }

    private fun initAdapter() {
        searchTopAdapter = SearchTopAdapter(requireContext())
        binding.rvSearchTopDestinations.apply {
            adapter = searchTopAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
        searchNearbyAdapter = SearchNearbyAdapter(requireContext())
        binding.rvSearchNearbyAttractions.apply {
            adapter = searchNearbyAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }
    private fun initOnSearchNearbyItemSelectedListener(){
        searchNearbyAdapter.onSearchNearbyItemItemSelected { selectedSearchNearbyItem ->
            val bundle = Bundle()
            bundle.putParcelable(TRAVEL_DETAIL, selectedSearchNearbyItem)
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }
    private fun initOnSearchTopItemSelectedListener(){
        searchTopAdapter.onSearchTopItemItemSelected { selectedSearchTopItem ->
            val bundle = Bundle()
            bundle.putParcelable(TRAVEL_DETAIL, selectedSearchTopItem)
            findNavController().navigate(R.id.action_searchFragment_to_detailFragment, bundle)
        }
    }
    companion object{
        const val TRAVEL_DETAIL = "TRAVEL_DETAIL"
    }
}