package com.example.sdc_bt1.model

import java.io.Serializable

data class LocationData(
    var latitude: Double? = null,
    var longitude: Double? = null
) : Serializable
