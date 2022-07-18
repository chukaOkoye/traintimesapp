package com.example.traintimes.network.model

import com.google.gson.annotations.SerializedName

data class Filter(
    @SerializedName("destination") val destination: Destination
)
