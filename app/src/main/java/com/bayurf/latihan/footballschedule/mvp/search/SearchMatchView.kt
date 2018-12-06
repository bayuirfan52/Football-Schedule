package com.bayurf.latihan.footballschedule.mvp.search

import com.bayurf.latihan.footballschedule.data.EventItem

interface SearchMatchView {
    fun showLoading()
    fun hideLoading()
    fun showQueryResult(data: List<EventItem>)
    fun showEmpty()
}