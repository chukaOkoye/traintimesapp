package com.example.traintimes

import com.google.gson.annotations.SerializedName

data class LocalStationModel(
    @SerializedName("stations") val stations: List<String>
)
