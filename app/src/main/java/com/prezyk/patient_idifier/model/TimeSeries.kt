package com.prezyk.patient_idifier.model

class TimeSeries() {

    var title: String? = null
    lateinit var dataSeries: Array<DoubleArray>
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TimeSeries

        if (title != other.title) return false
        if (!dataSeries.contentDeepEquals(other.dataSeries)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + dataSeries.contentDeepHashCode()
        return result
    }


//    constructor(title: String?, dataSeries: Array<IntArray>) : this() {
//        this.title = title
//        this.dataSeries = dataSeries
//    }
//
//
//    constructor(title: String?, length: Int) : this() {
//        this.title = t
//    }

}