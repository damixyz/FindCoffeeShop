package com.damixyz.findcoffee.venues

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.damixyz.domain.CoffeeShopInfo
import com.damixyz.findcoffee.R
import com.damixyz.findcoffee.databinding.ListVenuesBinding

class VenuesAdapter : ListAdapter<CoffeeShopInfo, VenuesAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<CoffeeShopInfo> = object :
            DiffUtil.ItemCallback<CoffeeShopInfo>() {
            override fun areItemsTheSame(
                oldItem: CoffeeShopInfo,
                newItem: CoffeeShopInfo
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: CoffeeShopInfo,
                newItem: CoffeeShopInfo
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent = parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = getItem(position))
    }

    class ViewHolder(private val binding: ListVenuesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: CoffeeShopInfo) {
            binding.venues = item
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding: ListVenuesBinding = DataBindingUtil
                    .inflate(layoutInflater, R.layout.list_venues, parent, false)
                return ViewHolder(binding)
            }
        }
    }

}
