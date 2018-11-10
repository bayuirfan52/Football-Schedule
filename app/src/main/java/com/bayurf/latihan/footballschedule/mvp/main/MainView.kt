package com.bayurf.latihan.footballschedule.mvp.main

import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.LeagueResponse

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showLastEventData(data : List<EventItem>)
    fun showNextEventData(data : List<EventItem>)
    fun getListLeagueData(leagueData: LeagueResponse)
}