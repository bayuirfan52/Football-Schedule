package com.bayurf.latihan.footballschedule.mvp.detail.view

import com.bayurf.latihan.footballschedule.data.TeamItem

interface DetailTeamView {
    fun showLoading()
    fun hideLoading()
    fun getTeamDetailData(data: List<TeamItem>)
}