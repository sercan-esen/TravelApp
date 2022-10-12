package com.example.travelapp.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentHomeBinding
import com.example.travelapp.presentation.core.BaseFragment
import com.example.travelapp.presentation.util.extension.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {
    private lateinit var homeAllAdapter: HomeAllAdapter

    override fun getLayoutRes(): Int = R.layout.fragment_home

    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        clickListeners()
        initAdapter()
        initObservers()
        viewModel.getAllTravels()
        homeSetupClickListener()
        initOnHomeItemSelectedListener()
        binding.tvHomeAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected_color))
    }

    private fun initObservers() {
        observe(viewModel.travelsData) {
            //println("Response:${it}")
            homeAllAdapter.updateList(it)
        }
    }


    private fun initAdapter() {
        homeAllAdapter = HomeAllAdapter(requireContext())
        binding.rvHomeDeals.apply {
            adapter = homeAllAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }
   private fun initOnHomeItemSelectedListener(){
        homeAllAdapter.onHomeItemSelected { selectedHomeItem ->
            val bundle = Bundle()
            bundle.putParcelable(TRAVEL_DETAIL, selectedHomeItem)
            findNavController().navigate(R.id.action_homeFragment_to_detailFragment, bundle)
        }
    }
    companion object{
        const val TRAVEL_DETAIL = "TRAVEL_DETAIL"
    }


    private fun homeSetupClickListener() {
        binding.flightsButton.setOnClickListener {
            showToastMessage("Fligts")
        }
        binding.hotelsButton.setOnClickListener {
            showToastMessage("Hotels")
        }
        binding.carsButton.setOnClickListener {
            showToastMessage("Cars")
        }
        binding.taxiButton.setOnClickListener {
            showToastMessage("Taxi")
        }
    }

    private fun clickListeners(){
        binding.tvHomeAll.apply {
            setOnClickListener {
                viewModel.getAllTravels()
                setTextColor(ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected_color))
                binding.tvHomeFlights.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeHotels.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeTransportations.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))

            }
        }
        binding.tvHomeFlights.apply{
            setOnClickListener {
                viewModel.getTravelsByCategory("flight")
                setTextColor(ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected_color))
                binding.tvHomeAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeHotels.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeTransportations.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))

            }

        }
        binding.tvHomeHotels.apply{
            setOnClickListener {
                viewModel.getTravelsByCategory("hotel")
                setTextColor(ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected_color))
                binding.tvHomeAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeFlights.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeTransportations.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
            }

        }
        binding.tvHomeTransportations.apply{
            setOnClickListener {
                viewModel.getTravelsByCategory("transportation")
                setTextColor(ContextCompat.getColor(requireContext(), R.color.bottom_nav_selected_color))
                binding.tvHomeAll.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeFlights.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
                binding.tvHomeHotels.setTextColor(ContextCompat.getColor(requireContext(), R.color.home_sub_text_color))
            }

        }

    }
}