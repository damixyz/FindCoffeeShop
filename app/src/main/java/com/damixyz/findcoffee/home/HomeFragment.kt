package com.damixyz.findcoffee.home

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.damixyz.findcoffee.R
import com.damixyz.findcoffee.databinding.FragmentHomeBinding
import com.damixyz.usecases.data.HomeScreen
import com.damixyz.usecases.data.ScreenState
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                homeViewModel.launchVenuesFragment()
                Timber.d("✅ is Granted")
            } else {
                Toast.makeText(activity, "Location Permission is Required.", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        homeViewModel.start()
        homeViewModel.homeScreenState.observe(viewLifecycleOwner, { screenState ->
            when (screenState) {
                is HomeScreen.LaunchVenues -> launchVenueFragment()
                is HomeScreen.Content -> processContent(screenState)
                is ScreenState.Empty -> processEmpty()
                is ScreenState.Error -> processError(screenState)
            }
        })

        binding.fab.setOnClickListener {
            when {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED -> {
                    homeViewModel.onFabSelected()
                }
                else -> {
                    requestPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            }
        }

        return binding.root
    }

    private fun launchVenueFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToVenuesFragment()
        findNavController().navigateUp()
        findNavController().navigate(action)
    }

    private fun processContent(screenState: HomeScreen.Content) {
        Timber.d("${screenState.payload}")
    }

    private fun processEmpty() {
        Toast.makeText(context, "No Data", Toast.LENGTH_LONG).show()
    }

    private fun processError(screenState: ScreenState.Error) {
        Timber.e(screenState.errorMessages)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}