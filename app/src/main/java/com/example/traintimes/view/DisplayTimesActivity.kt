package com.example.traintimes.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.traintimes.Constants
import com.example.traintimes.R
import com.example.traintimes.network.api.ApiUtilities
import com.example.traintimes.network.model.ModelClass
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DisplayTimesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_times)
        setSupportActionBar(findViewById(R.id.back_toolbar))


        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val backToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.back_toolbar)
        backToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        // Use intent from previous activity to retrieve input values
        val from: String = intent.getStringExtra(Constants.FROM_STATION).toString()
        val to: String = intent.getStringExtra(Constants.TO_STATION).toString()

        // Run method to fetch train times from API with converted CRS input as parameters
        fetchTrainTimes(from, to)
    }

    private fun fetchTrainTimes(station: String, toStation: String){

        // Calls Retrofit API methods and BasicAuth interceptors to make call to Realtime Trains API
        ApiUtilities.getApiInterface()?.getTrainTimesData(station, toStation)?.enqueue(object :
            Callback<ModelClass> {
            override fun onResponse(call: Call<ModelClass>, response: Response<ModelClass>) {
                if (response.isSuccessful){
                    Toast.makeText(applicationContext, "Success!", Toast.LENGTH_SHORT).show()
                    setDataOnViews(response.body())

                } else if (!response.isSuccessful) {
                    Toast.makeText(applicationContext, "No Response Received", Toast.LENGTH_SHORT).show()
                    setDataOnViews(response.body())
                    Log.e("DemoClass", "Error: ${response.code()} ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ModelClass>, t: Throwable) {
                Toast.makeText(applicationContext, "No Response on failure Received", Toast.LENGTH_SHORT).show()
                Log.e("DemoClass", "Error: ${t.message}")
                t.printStackTrace()
            }

        })
    }

    private fun setDataOnViews(body: ModelClass?) {

        // Targets Display Times layout id's
        val editableTime = findViewById<TextView>(R.id.editable_time)
        val editablePlatform = findViewById<TextView>(R.id.editable_platform)
        val editableFrom = findViewById<TextView>(R.id.editable_from)
        val editableTo = findViewById<TextView>(R.id.editable_destination)
        val serviceOperator = findViewById<TextView>(R.id.editable_serviceOperator)

        // Display change if JSON response returns null due to strikes or lack of service
        if(body?.services == null){
            findViewById<LinearLayout>(R.id.error_view).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.train_info).visibility = View.GONE
        } else {
            val currentTime1 = body.services!![0].locationDetail.gbttBookedDeparture
            val currentTime2 = body.services!![1].locationDetail.gbttBookedDeparture
            val currentTime3 = body.services!![2].locationDetail.gbttBookedDeparture
            val currentTime4 = body.services!![3].locationDetail.gbttBookedDeparture
            val currentTime5 = body.services!![4].locationDetail.gbttBookedDeparture






            val hour = currentTime1.substring(0, 2)
            val minutes = currentTime1.substring(2, 4)
            editableTime.text = getString(R.string.currentTime, hour, minutes)
            editablePlatform.text = body.services!![0].locationDetail.platform
            editableFrom.text = body.services!![0].locationDetail.description
            editableTo.text = body.services!![0].locationDetail.destination[0].description
            serviceOperator.text = body.services!![0].atocName
        }

    }
}

