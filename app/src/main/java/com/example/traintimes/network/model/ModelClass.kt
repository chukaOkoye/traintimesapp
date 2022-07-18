package com.example.traintimes.network.model

import com.google.gson.annotations.SerializedName


data class ModelClass(
    @SerializedName("services") var services: List<Services>?,
    @SerializedName("filter") var filter: Filter
    )