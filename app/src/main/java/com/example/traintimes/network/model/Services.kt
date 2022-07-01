package com.example.traintimes.network.model

import com.google.gson.annotations.SerializedName

data class Services(
    @SerializedName("locationDetail") val locationDetail: LocationDetail,
    @SerializedName("atocName") val atocName: String
)
