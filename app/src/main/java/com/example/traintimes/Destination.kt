package com.example.traintimes

import com.google.gson.annotations.SerializedName

data class Destination (
    @SerializedName("publicTime") val publicTime: String,
    @SerializedName("description") val description: String

    )
