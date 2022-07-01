package com.example.traintimes

import com.google.gson.annotations.SerializedName

data class ModelClass(
    @SerializedName("services") var services: List<Services>?
    )