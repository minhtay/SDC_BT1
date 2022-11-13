package com.example.sdc_bt1.model

import java.io.Serializable

data class LocationRuntime(
    var id: String? = null,
    var timeStar: Long? = null,
    var latitudeStart: Double? = null,
    var longitudeStart: Double? = null
) : Serializable