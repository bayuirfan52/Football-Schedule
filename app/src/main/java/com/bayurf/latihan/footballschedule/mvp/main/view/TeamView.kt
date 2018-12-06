package com.bayurf.latihan.footballschedule.mvp.main.view

import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.data.TeamItem

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun getTeamList(data: List<TeamItem>)
    fun showLeagues(data: LeagueResponse)
    fun showEmpty()
}