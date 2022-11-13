package com.example.sdc_bt1.model

import java.io.Serializable

data class RCVHistoryData(
    val date: String? = null,
    val data: ArrayList<LocationData>? = null
):Serializable

