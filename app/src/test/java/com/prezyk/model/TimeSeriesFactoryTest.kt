package com.prezyk.model

import com.prezyk.patient_idifier.model.TimeSeries
import com.prezyk.patient_idifier.model.TimeSeriesFactory
import org.junit.Test

class TimeSeriesFactoryTest {


    @Test
    fun getTSArrayFromStringTest() {
        var ts1 = TimeSeries()
        var ts2 = TimeSeries()
        var ts3 = TimeSeries()

        ts1.title = "series1"
        ts2.title = "series2"
        ts3.title = "series3"

        var timeArray = DoubleArray(10) { i -> 2.0*i }
        var data1Array = DoubleArray(10) { i -> i*5.0}
        var data2Array = DoubleArray(10) {i -> 0.5}
        var data3Array = DoubleArray(10) { i -> i*1.0}

        ts1.dataSeries = arrayOf(timeArray, data1Array)
        ts2.dataSeries = arrayOf(timeArray, data2Array)
        ts3.dataSeries = arrayOf(timeArray, data3Array)

        var modelArray = arrayOf(ts1, ts2, ts3)

        var inputString = "t,series1,series2,series3\n" +
                "0,0,0.5,0\n" +
                "2.0,5,0.5,1.0\n" +
                "4.0,10,0.5,2.0\n" +
                "6.0,15,0.5,3.0\n" +
                "8.0,20,0.5,4.0\n" +
                "10.0,25,0.5,5.0\n" +
                "12.0,30,0.5,6.0\n" +
                "14.0,35,0.5,7.0\n" +
                "16.0,40,0.5,8.0\n" +
                "18.0,45,0.5,9.0"

        var testArray = TimeSeriesFactory.getTSArrayFromString(inputString)

        assert(modelArray.contentDeepEquals(testArray))

    }



}