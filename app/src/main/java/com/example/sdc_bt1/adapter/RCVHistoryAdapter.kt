package com.example.sdc_bt1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sdc_bt1.databinding.ItemRcvHistoryBinding
import com.example.sdc_bt1.model.RCVHistoryData

class RCVHistoryAdapter(
    private val activity: Context,
    private val arrayList: ArrayList<RCVHistoryData>,
    private val onClick: (Any) -> Unit
) : RecyclerView.Adapter<RCVHistoryAdapter.Viewholder>() {
    class Viewholder(private val binding: ItemRcvHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            ItemRcvHistoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }
}