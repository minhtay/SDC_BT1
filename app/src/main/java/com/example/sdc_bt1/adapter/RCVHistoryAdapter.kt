package com.example.sdc_bt1.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sdc_bt1.databinding.ItemRcvHistoryBinding
import com.example.sdc_bt1.model.RCVHistoryData

class RCVHistoryAdapter(
    private val activity: Context,
    private val arrayList: ArrayList<RCVHistoryData>,
    private val onClick: (Any) -> Unit,
) : RecyclerView.Adapter<RCVHistoryAdapter.Viewholder>() {
    class Viewholder(val binding: ItemRcvHistoryBinding) : RecyclerView.ViewHolder(binding.root)

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
        var btn = false
        val data: RCVHistoryData = arrayList[position]
        holder.binding.tvDate.text = data.date
        holder.binding.tvWorkSize.text = data.data!!.size.toString()
        holder.binding.btnList.setOnClickListener {
            if (!btn) {
                btn = true
                holder.binding.rcvListLayout.visibility = View.VISIBLE
            } else {
                btn = false
                holder.binding.rcvListLayout.visibility = View.GONE
            }
        }

//        holder.binding.rcvListwork.layoutManager =
//            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
//        holder.binding.rcvListwork.adapter = RCVListWorkAdapter(activity, data.data)
    }

    override fun getItemCount(): Int {
        return arrayList?.size ?: 0
    }
}