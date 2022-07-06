package com.example.traintimes.view.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.traintimes.StationCodeMapper
import com.example.traintimes.network.model.LocalStationModel
import com.google.gson.GsonBuilder
import java.io.IOException

class MainActivityViewModel : ViewModel() {

    // Returns local Model of station JSON list
    fun getLocalJsonForAutocomplete(json: String) : LocalStationModel? {
        try {
            val gson = GsonBuilder().create()
            return gson.fromJson(json, LocalStationModel::class.java)
        } catch (e: IOException) {
            Log.e("Chuka", e.message.toString())
        }
        return null
    }

    // Returns CRS code of each station name when called
    fun mapToStationCode(stationName: String): String? {
        return StationCodeMapper.stationCodes[stationName]
    }
}

// One input for tests, two potential outputs (either valid or null)
// Another test mapToStationCode for valid and null
