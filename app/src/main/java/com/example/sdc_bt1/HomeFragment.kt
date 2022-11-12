package com.example.sdc_bt1

import android.annotation.SuppressLint
import android.location.Location
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sdc_bt1.databinding.FragmentHomeBinding
import com.example.sdc_bt1.model.LocationData
import com.example.sdc_bt1.model.LocationRuntime
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.schedule
import kotlin.concurrent.timerTask

class HomeFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentHomeBinding
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var lat: Double? = null
    private var lon: Double? = null
    private var codeAction: Int = 0
    private var codeBtn: Int = 0
    private var date: String = ""
    private var timeStamp: Long = 0
    private var id : String = ""
    private lateinit var runnable :Runnable
    private lateinit var handler:Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        getLocation()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        handler = Handler()
        runnable = Runnable(){
            getTime()
            getLocation()
            uploadLocationRuntime()
            handler.postDelayed(runnable,10000)
        }

        binding.btnAction.setOnClickListener {
            getTime()
            btnActionEvent()
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    @SuppressLint("MissingPermission")
    private fun getLocation() {
        fusedLocationClient.getCurrentLocation(
            LocationRequest.PRIORITY_HIGH_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            })
            .addOnSuccessListener { location: Location? ->
                run {
                    if (location != null) {
                        lat = location?.latitude
                        lon = location?.longitude
                    }
                }
            }
    }

    private fun btnActionEvent() {
        if (codeBtn == 0) {
            binding.btnAction.setText(R.string.btn_action_stop)
            codeBtn = 1
            codeAction = 1
            checkAction()
        } else {
            binding.btnAction.setText(R.string.btn_action_start)
            codeBtn = 0
            codeAction = 3
            checkAction()
        }
    }

    private fun getTime() {
        timeStamp = System.currentTimeMillis()
        val sdf = SimpleDateFormat("dd-MM-yyyy",Locale.getDefault())
        date = sdf.format(timeStamp)
    }

    private fun checkAction() {
        getTime()
        getLocation()
        when (codeAction){
            1->{
                uploadLocationStart()
                handler.post(runnable)
            }
            2->{
                uploadLocationFinish()
                handler.removeCallbacks(runnable)
            }
        }
    }

    private fun uploadLocationStart() {
        id = UUID.randomUUID().toString()
        val data = LocationData(id,date,timeStamp,lat,lon)
        FirebaseDatabase.getInstance().getReference("Location")
            .child(date).child(id).setValue(data)
    }

    private fun uploadLocationRuntime() {
        val idd = UUID.randomUUID().toString()
        val data = LocationRuntime(id,timeStamp,lat,lon)
        FirebaseDatabase.getInstance().getReference("Location")
            .child("$date/$id/$idd").setValue(data)
    }

    private fun uploadLocationFinish() {
        val idd = UUID.randomUUID().toString()
        val data = LocationRuntime(id,timeStamp,lat,lon)
        FirebaseDatabase.getInstance().getReference("Location")
            .child(date).child(idd).setValue(data)
    }
}