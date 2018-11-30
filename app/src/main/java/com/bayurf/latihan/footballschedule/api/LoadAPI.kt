package com.bayurf.latihan.footballschedule.api

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.net.URL

class LoadAPI {

    fun doRequest(url: String): Deferred<String> = GlobalScope.async {
        URL(url).readText()
    }
}