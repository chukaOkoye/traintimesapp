package com.example.traintimes.view

// import android.widget.EditText
// import android.widget.TextView

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import com.example.traintimes.Constants
import com.example.traintimes.R
import com.example.traintimes.databinding.ActivityMainBinding
import com.example.traintimes.view.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        viewModel = MainActivityViewModel()

        val fromStation : AutoCompleteTextView = binding?.fromStation!!
        val toStation: AutoCompleteTextView = binding?.toStation!!

        val parsedStations = viewModel.getLocalJsonForAutocomplete(assets.open("stations.json"))

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, parsedStations?.stations.orEmpty())
        binding!!.fromStation.setAdapter(adapter)
        binding!!.toStation.setAdapter(adapter)

        binding?.searchBox?.setOnClickListener {

            val fromStationCode = viewModel.mapToStationCode(fromStation.text.toString())
            val toStationCode = viewModel.mapToStationCode(toStation.text.toString())

            if (fromStationCode == null || toStationCode == null){
                fromStation.error = getString(R.string.station_name_error)
                toStation.error = getString(R.string.station_name_error)
            } else {
                val intent = Intent(this, DisplayTimesActivity::class.java)
                intent.putExtra(Constants.FROM_STATION, fromStationCode)
                intent.putExtra(Constants.TO_STATION, toStationCode)
                startActivity(intent)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

// Make is user-proof, drop down list
// Library for drop down lists autocompletetextview


