package com.bayurf.latihan.footballschedule.mvp.main

import com.bayurf.latihan.footballschedule.data.EventItem

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<EventItem>)
    fun showFavorite(data: List<EventItem>)
    fun showEmpty()
}