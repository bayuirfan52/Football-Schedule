package com.bayurf.latihan.footballschedule.mvp.main.presenter

import android.content.Context
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.api.TheSportsDBApi
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.data.TeamResponse
import com.bayurf.latihan.footballschedule.helper.database
import com.bayurf.latihan.footballschedule.mvp.main.view.TeamView
import com.bayurf.latihan.footballschedule.utils.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class TeamPresenter(
    private val view: TeamView,
    private val gson: Gson,
    private val loadAPI: LoadAPI,
    private val context: CoroutineContextProvider = CoroutineContextProvider()
) {
    fun getAllLeagues() {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getAllLeagues()),
                LeagueResponse::class.java
            )

            view.showLeagues(data)
            view.hideLoading()
        }
    }

    fun getAllTeams(leagueName: String?) {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.getAllTeams(leagueName)),
                TeamResponse::class.java
            )

            view.getTeamList(data.teams)
            view.hideLoading()
        }
    }

    fun showFavoriteData(funContext: Context?) {
        val teamItem: MutableList<TeamItem> = mutableListOf()
        teamItem.clear()
        funContext?.database?.use {
            val favorite = select(TeamItem.TABLE_TEAM)
                .parseList(classParser<TeamItem>())
            teamItem.addAll(favorite)
        }

        if (teamItem.size > 0) {
            view.getTeamList(teamItem)
        } else {
            view.showEmpty()
        }
    }

    fun getTeamResult(name: String = "") {
        view.showLoading()

        GlobalScope.launch(context.main) {
            val data = gson.fromJson(
                loadAPI
                    .doRequest(TheSportsDBApi.searchTeam(name)),
                TeamResponse::class.java
            )

            view.hideLoading()

            try {
                view.getTeamList(data.teams)
            } catch (e: Exception) {
                view.showEmpty()
            }
        }
    }
}