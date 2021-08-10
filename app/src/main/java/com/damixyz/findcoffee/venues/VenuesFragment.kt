package com.damixyz.findcoffee.venues

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.damixyz.findcoffee.R
import com.damixyz.findcoffee.databinding.FragmentVenuesBinding


class VenuesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVenuesBinding.inflate(inflater, container, false)

        return binding.root
    }
}