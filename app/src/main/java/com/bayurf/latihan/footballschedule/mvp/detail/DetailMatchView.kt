package com.bayurf.latihan.footballschedule.mvp.detail

import com.bayurf.latihan.footballschedule.data.TeamItem

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showImageBadgeExtra(idHomeTeam: List<TeamItem>, idAwayTeam: List<TeamItem>)
}