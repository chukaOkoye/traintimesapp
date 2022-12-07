package com.example.traintimes

import android.widget.TextView
import com.example.traintimes.network.model.*
import com.example.traintimes.view.DisplayTimesActivity
import junit.framework.Assert.assertEquals
import org.junit.Test

class DisplayTimesActivityTest {

    @Test
    fun setDataOnViews_PopulatesViews() {
        // Create an instance of DisplayTimesActivity
        val activity = DisplayTimesActivity()

        // Create a mock ModelClass object with sample data
        val modelClass = ModelClass(
            services = listOf(
                Services(
                    locationDetail = LocationDetail(
                        gbttBookedDeparture = "1030",
                        platform = "1",
                        description = "London Victoria",
                        destination = "Brighton"
                    ),
                    atocName = "Southern"
                )
            ),
            filter = Filter(
                    destination = Destination(
                        publicTime = "2222",
                        description = "Train",
                        name = "Brighton")
                )
            )
        )

        // Call setDataOnViews() with the mock ModelClass object
        activity.setDataOnViews(modelClass)

        // Verify that the views are populated with the correct data
        assertEquals("10:30", activity.findViewById<TextView>(R.id.editable_time).text)
        assertEquals("1", activity.findViewById<TextView>(R.id.editable_platform).text)
        assertEquals("London Victoria", activity.findViewById<TextView>(R.id.editable_from).text)
        assertEquals("Southern", activity.findViewById<TextView>(R.id.editable_serviceOperator).text)
        assertEquals("Brighton", activity.findViewById<TextView>(R.id.editable_destination).text)
    }
}