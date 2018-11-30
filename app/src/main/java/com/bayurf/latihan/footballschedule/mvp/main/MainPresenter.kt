package com.bayurf.latihan.footballschedule.mvp.main

import android.content.Context
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.EventResponse
import com.bayurf.latihan.footballschedule.database.database
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class MainPresenter(
    val view: MainView,
    private val gson: Gson,
    private val loadAPI: LoadAPI,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    var section = 1

    fun showLastEventData(league : String){
        this.section = 1
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getLastEventData(league)).await(),
                EventResponse::class.java)

                view.hideLoading()
                try {
                    view.showMatch(data.events)
                } catch (e: NullPointerException) {
                    view.showEmpty()
                }
        }
    }

    fun showNextEventData(idLeague: String) {
        this.section = 2
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(loadAPI
                .doRequest(TheSportsDBApi.getNextEventData(idLeague)).await(),
                EventResponse::class.java)
                view.hideLoading()
                try {
                    view.showMatch(data.events)
                } catch (e: NullPointerException) {
                    view.showEmpty()
                }
        }
    }

    fun showFavoriteData(funContext: Context?) {
        val eventItem: MutableList<EventItem> = mutableListOf()
        eventItem.clear()
        funContext?.database?.use {
            val favorite = select(EventItem.TABLE_FAVORITE)
                .parseList(classParser<EventItem>())
            eventItem.addAll(favorite)
        }

        if (eventItem.size > 0) {
            view.showFavorite(eventItem)
        } else {
            view.showEmpty()
        }
    }
}