package com.bayurf.latihan.footballschedule.api

import java.net.URL

class LoadAPI {

    fun doRequest(url : String) : String{
        return URL(url).readText()
    }
}