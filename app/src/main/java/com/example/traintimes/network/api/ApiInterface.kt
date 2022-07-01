package com.example.traintimes.network.api

import com.example.traintimes.network.model.ModelClass
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("json/search/{station}/to/{toStation}")
    fun getTrainTimesData(
        @Path("station") station: String,
        @Path("toStation") toStation: String
    ): Call<ModelClass>
}