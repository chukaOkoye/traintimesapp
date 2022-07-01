package com.example.traintimes

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.traintimes.api.ApiUtilities
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

        fetchTrainTimes(from, to)
    }

    private fun fetchTrainTimes(station: String, toStation: String){

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

        val editableTime = findViewById<TextView>(R.id.editable_time)
        val editablePlatform = findViewById<TextView>(R.id.editable_platform)
        val editableFrom = findViewById<TextView>(R.id.editable_from)
        val editableTo = findViewById<TextView>(R.id.editable_destination)
        val serviceOperator = findViewById<TextView>(R.id.editable_serviceOperator)

        if(body?.services == null){
            findViewById<LinearLayout>(R.id.error_view).visibility = View.VISIBLE
            findViewById<LinearLayout>(R.id.train_info).visibility = View.GONE
        } else {
            val currentTime = body.services!![0].locationDetail.gbttBookedDeparture
            val hour = currentTime.substring(0, 2)
            val minutes = currentTime.substring(2, 4)
            editableTime.text = getString(R.string.currentTime, hour, minutes)
            editablePlatform.text = body.services!![0].locationDetail.platform
            editableFrom.text = body.services!![0].locationDetail.description
            editableTo.text = body.services!![0].locationDetail.destination[0].description
            serviceOperator.text = body.services!![0].atocName
        }

    }
}

