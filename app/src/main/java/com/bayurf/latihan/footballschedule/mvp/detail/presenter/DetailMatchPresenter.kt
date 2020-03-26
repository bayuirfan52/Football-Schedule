package com.bayurf.latihan.footballschedule.mvp.detail.presenter

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.data.TeamResponse
import com.bayurf.latihan.footballschedule.mvp.detail.view.DetailMatchView
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailMatchPresenter(
    val view: DetailMatchView,
    val gson: Gson,
    val loadAPI: LoadAPI,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {

    fun getTeamBadge(idHomeTeam : String, idAwayTeam : String){
        view.showLoading()

        GlobalScope.launch(context.main) {
            val imageHomeBadge = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getTeamDetail(idHomeTeam)),
                TeamResponse::class.java)

            val imageAwayBadge = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getTeamDetail(idAwayTeam)),
                TeamResponse::class.java)

                imageHomeBadge.teams.let { imageAwayBadge.teams.let { it1 -> view.showImageBadgeExtra(it, it1) } }
                view.hideLoading()
        }
    }

    fun getEventDetail(idEvent: String) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val eventDetail = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getEventDetails(idEvent)),
                EventResponse::class.java
            )

                view.showDetailData(eventDetail.events)
                view.hideLoading()
        }
    }
}