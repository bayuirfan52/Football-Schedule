package com.bayurf.latihan.footballschedule.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.URL

class LoadAPI {

    suspend fun doRequest(url: String): String =
        withContext(Dispatchers.Default) {
            URL(url).readText()
        }
}