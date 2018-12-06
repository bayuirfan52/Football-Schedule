package com.bayurf.latihan.footballschedule.mvp.detail.view

import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.TeamItem

interface DetailMatchView {
    fun showLoading()
    fun hideLoading()
    fun showDetailData(data: List<EventItem>)
    fun showImageBadgeExtra(idHomeTeam: List<TeamItem>, idAwayTeam: List<TeamItem>)
}