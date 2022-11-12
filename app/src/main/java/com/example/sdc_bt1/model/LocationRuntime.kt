package com.example.sdc_bt1.model

import java.io.Serializable

data class LocationRuntime(
    val id: String? = null,
    val timeStamp: Long? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
) : Serializable