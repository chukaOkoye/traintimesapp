package com.example.traintimes.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.traintimes.StationCodeMapper
import com.example.traintimes.network.model.LocalStationModel
import com.google.gson.GsonBuilder
import java.io.IOException
import java.io.InputStream

class MainActivityViewModel : ViewModel() {

    fun getLocalJsonForAutocomplete(stations: InputStream) : LocalStationModel? {

        val json: String?

        try {

            json = stations.bufferedReader().use { it.readText() }

            val gson = GsonBuilder().create()
            return gson.fromJson(json, LocalStationModel::class.java)


        } catch (e: IOException) {
            Log.e("Chuka", e.message.toString())

        }

        return null

    }

    fun mapToStationCode(stationName: String): String? {
        return StationCodeMapper.stationCodes[stationName]
    }
}