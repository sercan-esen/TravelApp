package com.example.travelapp.presentation.guide

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentGuideBinding
import com.example.travelapp.presentation.core.BaseFragment
import com.example.travelapp.presentation.util.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GuideFragment : BaseFragment<FragmentGuideBinding, GuideViewModel>() {
    private lateinit var guideMightAdapter: GuideMightAdapter
    private lateinit var guideTopPickAdapter: GuideTopPickAdapter
    private lateinit var guideCategoryAdapter: GuideCategoryAdapter


    override fun getLayoutRes(): Int = R.layout.fragment_guide

    override fun getViewModelClass(): Class<GuideViewModel> = GuideViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initAdapter()
        viewModel.apply {
            getTravelsByCategoryMightNeed()
            getTravelsByCategoryTopPick()
            getGuideCategories()
        }
        initOnGuideMightItemSelectedListener()
        initOnGuideTopPickItemSelectedListener()
    }


    private fun initObservers() {
        observe(viewModel.mightNeedData) {
            guideMightAdapter.updateList(it)

        }
        observe(viewModel.topPickData) {
            guideTopPickAdapter.updateList(it)
        }
        observe(viewModel.guideCategoriesData){
            guideCategoryAdapter.updateList(it)
        }
    }

    private fun initAdapter() {
        guideMightAdapter = GuideMightAdapter(requireContext())
        binding.rvGuideMightNeedThese.apply {
            adapter = guideMightAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        guideTopPickAdapter = GuideTopPickAdapter(requireContext())
        binding.rvTopPickArticles.apply {
            adapter = guideTopPickAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        guideCategoryAdapter = GuideCategoryAdapter(requireContext())
        binding.rvGuideCategory.apply {
            adapter = guideCategoryAdapter
            layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        }
    }

    private fun initOnGuideTopPickItemSelectedListener() {
        guideTopPickAdapter.onGuideTopPickItemItemSelected { selectedGuideTopPickItem ->
            val bundle = Bundle()
            bundle.putParcelable(TRAVEL_DETAIL, selectedGuideTopPickItem)
            findNavController().navigate(R.id.action_guideFragment_to_detailFragment, bundle)
        }
    }

    private fun initOnGuideMightItemSelectedListener() {
        guideMightAdapter.onGuideMightItemItemSelected { selectedGuideMightItem ->
            val bundle = Bundle()
            bundle.putParcelable(TRAVEL_DETAIL, selectedGuideMightItem)
            findNavController().navigate(R.id.action_guideFragment_to_detailFragment, bundle)
        }
    }

    companion object {
        const val TRAVEL_DETAIL = "TRAVEL_DETAIL"
    }


}