package com.bayurf.latihan.footballschedule

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.EventSearchResponse
import com.bayurf.latihan.footballschedule.mvp.search.SearchMatchPresenter
import com.bayurf.latihan.footballschedule.mvp.search.SearchMatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SearchEventTest {
    @Mock
    private lateinit var view: SearchMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var loadAPI: LoadAPI

    private lateinit var presenter: SearchMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = SearchMatchPresenter(view, gson, loadAPI, TestCoroutineContextProvider())
    }

    @Test
    fun searchEventTest() {
        val event: MutableList<EventItem> = mutableListOf()
        val response = EventSearchResponse(event)
        val value = "Barcelona"

        GlobalScope.launch {
            `when`(
                gson.fromJson(
                    loadAPI
                        .doRequest(TheSportsDBApi.searchEvent(value)).await(),
                    EventSearchResponse::class.java
                )
            ).thenReturn(response)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showQueryResult(event)
            Mockito.verify(view).hideLoading()
        }
    }
}