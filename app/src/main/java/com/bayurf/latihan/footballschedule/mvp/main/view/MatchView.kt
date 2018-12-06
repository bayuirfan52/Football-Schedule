package com.bayurf.latihan.footballschedule.mvp.main.view

import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.LeagueResponse

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<EventItem>)
    fun showLeagues(data: LeagueResponse)
    fun showEmpty()
}