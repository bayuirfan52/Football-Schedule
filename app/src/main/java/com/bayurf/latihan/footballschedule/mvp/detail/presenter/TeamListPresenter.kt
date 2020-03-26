package com.bayurf.latihan.footballschedule.mvp.detail.presenter

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.PlayerResponse
import com.bayurf.latihan.footballschedule.mvp.detail.view.TeamListView
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamListPresenter(
    val view: TeamListView,
    val gson: Gson,
    val loadAPI: LoadAPI,
    val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getPlayerList(id: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getPlayers(id)),
                PlayerResponse::class.java
            )

            view.hideLoading()
            try {
                view.showListData(data.player)
            } catch (e: Exception) {
                view.hideLoading()
            }
        }
    }
}