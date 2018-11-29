package com.bayurf.latihan.footballschedule.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class LoadAPITest {
    @Test
    fun testDoRequest() {
        val loadApi = mock(LoadAPI::class.java)
        val url = "https://www.thesportsdb.com/api/v1/json/1/eventspastleague.php?id=4328"
        loadApi.doRequest(url)
        verify(loadApi).doRequest(url)
    }
}