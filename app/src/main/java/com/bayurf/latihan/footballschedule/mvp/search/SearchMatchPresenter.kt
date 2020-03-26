package com.bayurf.latihan.footballschedule.mvp.search

import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventSearchResponse
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchMatchPresenter(
    val view: SearchMatchView,
    val gson: Gson,
    val loadAPI: LoadAPI,
    val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getEventResult(name: String = "") {
        view.showLoading()
        val newFormat = name.replace(" ", "%20")

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.searchEvent(newFormat)),
                EventSearchResponse::class.java
            )

            view.hideLoading()
            try {
                view.showQueryResult(data.events)
            } catch (e: Exception) {
                view.showEmpty()
            }
        }
    }
}