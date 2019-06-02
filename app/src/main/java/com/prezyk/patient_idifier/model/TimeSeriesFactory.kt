package com.prezyk.patient_idifier.model

class TimeSeriesFactory() {

    companion object {

        val DATA_SEPARATOR = ","

        fun getTSArrayFromString(data: String): Array<TimeSeries> {
            var splitData = data.split(System.getProperty("line.separator"))
            var titleLine = splitData[0].split(DATA_SEPARATOR)

            var tsArray = Array(titleLine.size - 1) { TimeSeries() }

            for (i in 1 until titleLine.size) {
                tsArray[i - 1].title = titleLine[i]
                tsArray[i - 1].dataSeries = Array(2) { DoubleArray(splitData.size - 1) }
            }


            for (i in 1 until splitData.size-1) {
                var dataLine = splitData[i].split(DATA_SEPARATOR)
                for (j in 0 until tsArray.size) {
                    tsArray[j].dataSeries[0][i-1] = dataLine[0].toDouble()
                    tsArray[j].dataSeries[1][i-1] = dataLine[j + 1].toDouble()
                }
            }

            tsArray = arrayOf(tsArray[0], tsArray[1], tsArray[2])

            return tsArray
        }
    }



}