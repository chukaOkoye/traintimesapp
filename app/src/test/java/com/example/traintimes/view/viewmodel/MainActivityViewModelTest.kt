package com.example.traintimes.view.viewmodel

import com.example.traintimes.network.model.LocalStationModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNull
import org.junit.Test


// given, when, then
class MainActivityViewModelTest {
    private val viewModel = MainActivityViewModel()

    @Test
    fun `given valid JSON, will return LocalStationModel`() {

        val expected = LocalStationModel(
            listOf(
                "Abbey Wood",
                "Aber",
                "Abercynon"
            )
        )
        val actual = viewModel.getLocalJsonForAutocomplete(validMockJson)
        assertEquals(expected, actual)
    }

    @Test
    fun `given invalid JSON, will return null`() {

        val actual = viewModel.getLocalJsonForAutocomplete(invalidMockJson)
        assertNull(actual?.stations)
    }

    @Test
    fun `given station name, return correct CRS code`(){
        val expected = viewModel.mapToStationCode("London Victoria")
        val actual = ("VIC")

        assertEquals(expected, actual)
    }

    @Test
    fun `given invalid station, return null`(){
        val expected = viewModel.mapToStationCode("London")
        val actual = null

        assertEquals(expected, actual)
    }

    private val validMockJson = """
      {
        "stations": 
        [
            "Abbey Wood",
            "Aber",
            "Abercynon"
        ]
      }
    """.trimIndent()

    private val invalidMockJson = """
      {
        "places": 
        [
            "Abbey Wood",
            "Aber",
            "Abercynon"
        ]
      }
    """.trimIndent()
}

// write the rests of the tests for both functions