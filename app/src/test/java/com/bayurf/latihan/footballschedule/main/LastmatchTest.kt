package com.bayurf.latihan.footballschedule.main

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.mvp.main.MainPresenter
import com.bayurf.latihan.footballschedule.mvp.main.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class LastmatchTest {
    @Mock
    private lateinit var view: MainView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var loadAPI: LoadAPI

    private lateinit var presenter: MainPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = MainPresenter(view, gson, loadAPI)
    }

    @Test
    fun testGetLastEventList() {
        val events: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(events)
        val id = "4328"

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    loadAPI
                        .doRequest(TheSportsDBApi.getLastEventData(id)),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showMatch(events)
            Mockito.verify(view).hideLoading()
        }
    }
}