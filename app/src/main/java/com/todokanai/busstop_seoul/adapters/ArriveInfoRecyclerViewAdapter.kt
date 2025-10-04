package com.todokanai.busstop_seoul.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.todokanai.busstop_seoul.databinding.ArriveInfoRecyclerBinding
import com.todokanai.busstop_seoul.dataclass.BusArriveInfo
import com.todokanai.busstop_seoul.holders.ArriveInfoRecyclerViewHolder

class ArriveInfoRecyclerViewAdapter(): ListAdapter<BusArriveInfo, ArriveInfoRecyclerViewHolder>(
    object : DiffUtil.ItemCallback<BusArriveInfo>(){
        override fun areItemsTheSame(
            oldItem: BusArriveInfo,
            newItem: BusArriveInfo
        ): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(
            oldItem: BusArriveInfo,
            newItem: BusArriveInfo
        ): Boolean {
            return oldItem == newItem
        }
    }
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ArriveInfoRecyclerViewHolder {
        val binding =
            ArriveInfoRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArriveInfoRecyclerViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: ArriveInfoRecyclerViewHolder,
        position: Int
    ) {

    }
}