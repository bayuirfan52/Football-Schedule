package com.bayurf.latihan.footballschedule.mvp.main

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(val view: MainView, private val gson: Gson, private val loadAPI: LoadAPI){
    var section = 1

    fun getAllLeague(){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getAllLeagues()),
                LeagueResponse::class.java)

            uiThread {
                view.hideLoading()
                view.getListLeagueData(data)
            }
        }
    }

    fun showLastEventData(league : String){
        this.section = 1
        view.showLoading()

        doAsync {
            val data = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getLastEventData(league)),
                EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showLastEventData(data.events)
            }
        }
    }

    fun showNextEventData(idLeague: String) {
        this.section = 2
        view.showLoading()

        doAsync {
            val data = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getNextEventData(idLeague)),
                EventResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showNextEventData(data.events)
            }
        }
    }

}