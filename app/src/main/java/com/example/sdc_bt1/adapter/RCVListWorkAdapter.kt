package com.example.sdc_bt1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sdc_bt1.databinding.ItemListWorkBinding
import com.example.sdc_bt1.model.LocationData

class RCVListWorkAdapter(
    private val activity: Context,
    private val arrayList: ArrayList<LocationData>,
):RecyclerView.Adapter<RCVListWorkAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemListWorkBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListWorkBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}