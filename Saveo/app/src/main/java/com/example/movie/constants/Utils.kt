package com.example.movie.constants

object Utils {

    fun getTimeConverted( inTime : Int) : String{

        val hours = inTime.toInt() / 3600
        var temp = inTime.toInt() - hours * 3600
        val mins = temp / 60
        temp = temp - mins * 60
        val secs = temp

        val requiredFormat = "$hours h : $mins m: $secs s" //hh:mm:ss formatted string

        return requiredFormat

    }
}