package com.example.traintimes.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiUtilities {

    private var retrofit:Retrofit? = null
    private var BASE_URL = "https://api.rtt.io/api/v1/"

    private val client =  OkHttpClient.Builder()
        .addInterceptor(BasicAuthInterceptor("rttapi_coo990", "d58e9ef7ee15ce92fa0a3d74e38afec90a248ea5"))
        .addInterceptor(HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        .build()

    private val gson = GsonBuilder()
        //.setLenient()
        .create()

    fun getApiInterface(): ApiInterface?{
        if (retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
        }

        return retrofit!!.create(ApiInterface::class.java)
    }
}