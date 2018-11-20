package com.bayurf.latihan.footballschedule.mvp.detail

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.data.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailMatchPresenter(val view : DetailMatchView, val gson: Gson, val loadAPI: LoadAPI) {

    fun getTeamBadge(idHomeTeam : String, idAwayTeam : String){
        view.showLoading()

        doAsync {
            val imageHomeBadge = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getTeamDetail(idHomeTeam)),
                TeamResponse::class.java)

            val imageAwayBadge = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getTeamDetail(idAwayTeam)),
                TeamResponse::class.java)

            uiThread {
                imageHomeBadge.teams.let { imageAwayBadge.teams.let { it1 -> view.showImageBadgeExtra(it, it1) } }
                view.hideLoading()
            }
        }
    }

    fun getEventDetail(idEvent: String) {
        view.showLoading()

        doAsync {
            val eventDetail = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getEventDetails(idEvent)),
                EventResponse::class.java
            )

            uiThread {
                view.showDetailData(eventDetail.events)
                view.hideLoading()
            }
        }
    }
}