package com.example.traintimes.network.model

import com.google.gson.annotations.SerializedName

data class LocationDetail (
    @SerializedName("platform") val platform: String,
    @SerializedName("description") val description: String,
    @SerializedName("destination") val destination: List<Destination>,
    @SerializedName("gbttBookedDeparture") val gbttBookedDeparture: String

)
