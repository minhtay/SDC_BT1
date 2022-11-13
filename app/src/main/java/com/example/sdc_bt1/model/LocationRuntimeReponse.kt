package com.example.sdc_bt1.model

import java.io.Serializable

data class LocationRuntimeReponse(
    var id: String? = null,
    var date: String? = null,
    var timeStar: Long? = null,
    var latitudeStart: Double? = null,
    var longitudeStart: Double? = null
) : Serializable