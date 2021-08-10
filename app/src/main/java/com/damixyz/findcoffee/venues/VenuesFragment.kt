package com.damixyz.findcoffee.venues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.damixyz.findcoffee.R
import com.damixyz.findcoffee.databinding.FragmentVenuesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VenuesFragment : Fragment() {

    private var _binding: FragmentVenuesBinding? = null
    private val binding get() = _binding!!

    private val venuesViewModel: VenuesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVenuesBinding.inflate(inflater, container, false)

        venuesViewModel.getVenues()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}