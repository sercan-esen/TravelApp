package com.example.travelapp.presentation.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.travelapp.R
import com.example.travelapp.data.entity.Travel
import com.example.travelapp.databinding.FragmentDetailBinding
import com.example.travelapp.presentation.home.HomeFragment.Companion.TRAVEL_DETAIL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private var travelAppDetail: Travel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_detail,container,false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgs()
        binding.apply {
            ivDetail.loadWithGlide(travelAppDetail!!.images.get(0).url)
            tvDetailTitle.text = travelAppDetail!!.title
            tvDetailCity.text = "${travelAppDetail!!.city},"
            tvDetailCountry.text = travelAppDetail!!.country
            tvDetailDesc.text = travelAppDetail!!.description
        }

    }

    private fun getArgs() {
        if (arguments?.isEmpty != true) {
            arguments?.let {
                travelAppDetail = it.getParcelable<Travel>(TRAVEL_DETAIL)

            }
        }
    }
    private fun ImageView.loadWithGlide(imageUrl: String) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(this)
    }


}