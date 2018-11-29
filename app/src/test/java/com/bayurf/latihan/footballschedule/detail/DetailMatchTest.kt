package com.bayurf.latihan.footballschedule.detail

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.mvp.detail.DetailMatchPresenter
import com.bayurf.latihan.footballschedule.mvp.detail.DetailMatchView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class DetailMatchTest {
    @Mock
    private lateinit var view: DetailMatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var loadAPI: LoadAPI

    private lateinit var presenter: DetailMatchPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = DetailMatchPresenter(view, gson, loadAPI)
    }

    @Test
    fun testGetTeamDetail() {
        val events: MutableList<EventItem> = mutableListOf()
        val response = EventResponse(events)
        val id = "441613"

        doAsync {
            Mockito.`when`(
                gson.fromJson(
                    loadAPI
                        .doRequest(TheSportsDBApi.getEventDetails(id)),
                    EventResponse::class.java
                )
            ).thenReturn(response)

            presenter.getEventDetail(id)

            Mockito.verify(view).showLoading()
            Mockito.verify(view).showDetailData(events)
            Mockito.verify(view).hideLoading()
        }
    }
}