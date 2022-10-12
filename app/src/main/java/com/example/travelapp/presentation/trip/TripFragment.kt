package com.example.travelapp.presentation.trip

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.travelapp.R
import com.example.travelapp.databinding.FragmentTripBinding

class TripFragment : Fragment() {
    private lateinit var binding: FragmentTripBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_trip, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        defaultTripButtonColor()
        clickListeners()
    }

    private fun defaultTripButtonColor() {
        binding.btnTrips.apply {
            setIconTintResource(R.color.detail_selected_color)
            setTextColor(ContextCompat.getColor(requireContext(), R.color.detail_selected_color))

        }
    }

    private fun clickListeners() {
        binding.btnTrips.apply {
            setOnClickListener {
                binding.tripFab.visibility = View.VISIBLE
                setIconTintResource(R.color.detail_selected_color)
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.detail_selected_color
                    )
                )
                binding.btnBookmark.apply {
                    setIconTintResource(R.color.detail_default_color)
                    setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.detail_default_color
                        )
                    )
                }
            }
        }
        binding.btnBookmark.apply {

            setOnClickListener {
                binding.tripFab.visibility = View.GONE
                setIconTintResource(R.color.detail_selected_color)
                setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.detail_selected_color
                    )
                )
                binding.btnTrips.apply {
                    setIconTintResource(R.color.detail_default_color)
                    setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.detail_default_color
                        )
                    )
                }
            }
        }
    }


}