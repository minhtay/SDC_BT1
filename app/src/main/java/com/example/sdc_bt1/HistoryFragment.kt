package com.example.sdc_bt1

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sdc_bt1.adapter.RCVHistoryAdapter
import com.example.sdc_bt1.databinding.FragmentHistoryBinding
import com.example.sdc_bt1.model.LocationData
import com.example.sdc_bt1.model.RCVHistoryData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var adapter: RCVHistoryAdapter
    private var dataReponse = ArrayList<RCVHistoryData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initview()
        readData()
    }

    private fun initview() {
        binding.rcvHistory.layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)
        adapter = RCVHistoryAdapter(requireContext(), dataReponse) { data ->
            onClickItem(data)
        }
        binding.rcvHistory.adapter = adapter
    }

    private fun onClickItem(data: Any) {
        TODO("Not yet implemented")
    }

    private fun readData() {
        var map = HashMap<String, HashMap<String, Any>>()
        var map1 = HashMap<String, Any>()
        var arr = ArrayList<LocationData>()
        FirebaseDatabase.getInstance().getReference("Location")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        map.clear()
                        map1.clear()
                        for (data in snapshot.children) {
                            map = data.value as HashMap<String, HashMap<String, Any>>
                            map.keys.forEach {
                                map1 = map[it]!!
                                arr.add(
                                    LocationData(
                                        map1["id"].toString(),
                                        map1["date"].toString(),
                                        map1["timeStar"].toString().toLongOrNull()
                                    )
                                )
                            }
                            dataReponse.add(RCVHistoryData(data.key.toString(),arr))
                            adapter.notifyDataSetChanged()
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {}

            })


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHistoryBinding.inflate(layoutInflater)
        return binding.root
    }


}