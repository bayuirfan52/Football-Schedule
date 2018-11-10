package com.bayurf.latihan.footballschedule.data

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("idLeague")
    var idLeague : String? = null,

    @SerializedName("strLeague")
    var strLeague : String? = null

){
    override fun toString() : String{
        return strLeague.toString()
    }
}