package com.prezyk.patient_idifier.time_series_result_display

import android.graphics.Color

class ChartColors {

    companion object{
        val RED = Color.RED
        val BLUE = Color.BLUE
        val GREEN = Color.GREEN
        val YELLOW = Color.YELLOW
        val DARK_GREEN = Color.rgb(0, 58,0)
        val DARK_BLUE = Color.rgb(0, 0, 58)
        val DARK_RED = Color.rgb(58, 0, 0)
        val MINT = Color.rgb(132, 255, 171)
        val BROWN = Color.rgb(191, 137, 70)
        val ORANGE = Color.rgb(255, 123, 0)
        val VIOLET = Color.rgb(255, 0, 255)
        val LIGHT_VIOLET = Color.rgb(212, 149, 255)
        val DARK_VIOLET = Color.rgb(68, 0, 93)
        val GRAY = Color.GRAY

        fun getColorArray(): IntArray {
            return intArrayOf(RED, BLUE, GREEN, YELLOW, ORANGE, BROWN, VIOLET, MINT, DARK_BLUE, DARK_RED, LIGHT_VIOLET, GRAY, DARK_GREEN, DARK_VIOLET)
        }
    }

}