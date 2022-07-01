package com.example.traintimes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
// import android.widget.EditText
// import android.widget.TextView
import com.example.traintimes.databinding.ActivityMainBinding
import com.google.gson.GsonBuilder
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        var fromStation : AutoCompleteTextView = binding?.fromStation!!
        val toStation: AutoCompleteTextView = binding?.toStation!!

        val json: String?

        try {
            val inputStream: InputStream = assets.open("stations.json")

            json = inputStream.bufferedReader().use { it.readText() }

            val gson = GsonBuilder().create()
            val parsedStations = gson.fromJson(json, LocalStationModel::class.java)
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, parsedStations.stations)
            binding!!.fromStation.setAdapter(adapter)
            binding!!.toStation.setAdapter(adapter)

        } catch (e: IOException) {
            Log.e("Chuka", e.message.toString())

        }

        binding?.searchBox?.setOnClickListener {
            val intent = Intent(this, DisplayTimesActivity::class.java)
            intent.putExtra(Constants.FROM_STATION, mapToStationCode(fromStation.text.toString()))
            intent.putExtra(Constants.TO_STATION, mapToStationCode(toStation.text.toString()))
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun mapToStationCode(name: String): String {

        return StationNameMap.stationCodes[name]?: name

    }

}

// Make is user-proof, drop down list
// Library for drop down lists autocompletetextview


