package com.damixyz.findcoffee.venues

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.findcoffee.databinding.FragmentVenuesBinding
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class VenuesFragment : Fragment() {

    private var _binding: FragmentVenuesBinding? = null
    private val binding get() = _binding!!

    private val venuesViewModel: VenuesViewModel by viewModels()

    private val viewAdapter = VenuesAdapter()

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVenuesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = activity
        binding.viewModel = venuesViewModel
        binding.rvList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
            adapter = viewAdapter
            setHasFixedSize(true)
        }

        createLocationRequest()

        binding.btnRetry.setOnClickListener {
            createLocationRequest()
        }

        venuesViewModel.venueInfo.observe(viewLifecycleOwner, {
            processContent(it)
        })
        venuesViewModel.activeError.observe(viewLifecycleOwner, {
            processError(it)
        })
        venuesViewModel.loadingObservable.observe(viewLifecycleOwner, {
            processLoading(it)
        })
//        venuesViewModel.getVenues()
        return binding.root
    }

    @SuppressLint("MissingPermission")
    private fun createLocationRequest() {
        // Request location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                val latLng = "${location.latitude}, ${location.longitude}"
                venuesViewModel.getVenues(latLng)
            }
        }

    }

    private fun processError(errorState: Boolean) {
        when (errorState) {
            true -> binding.llErrorContainer.visibility = View.VISIBLE
            false -> binding.llErrorContainer.visibility = View.GONE
        }
    }

    private fun processContent(payload: List<CoffeeShopInfo>) {
        viewAdapter.submitList(payload)
    }

    private fun processLoading(isLoading: Boolean) {
        when (isLoading) {
            true -> binding.pbLoading.visibility = View.VISIBLE
            false -> binding.pbLoading.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}