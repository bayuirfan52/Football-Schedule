package com.bayurf.latihan.footballschedule.mvp.detail.presenter

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.TeamResponse
import com.bayurf.latihan.footballschedule.mvp.detail.view.DetailTeamView
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailTeamPresenter(
    private val view: DetailTeamView,
    private val gson: Gson,
    private val loadAPI: LoadAPI,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getTeamDetail(id: String?) {
        view.hideLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getTeamDetail(id)),
                TeamResponse::class.java
            )

            view.hideLoading()
            try {
                view.getTeamDetailData(data.teams)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}