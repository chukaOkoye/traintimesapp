package com.example.traintimes.network.model

import com.google.gson.annotations.SerializedName

data class LocalStationModel(
    @SerializedName("stations") val stations: List<String>
)
