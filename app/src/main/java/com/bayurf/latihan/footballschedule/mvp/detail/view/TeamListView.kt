package com.bayurf.latihan.footballschedule.mvp.detail.view

import com.bayurf.latihan.footballschedule.data.PlayerItem

interface TeamListView {
    fun showLoading()
    fun hideLoading()
    fun showListData(data: List<PlayerItem>)
    fun showEmpty()
}