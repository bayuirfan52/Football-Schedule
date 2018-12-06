package com.bayurf.latihan.footballschedule.api

import com.bayurf.latihan.footballschedule.BuildConfig

object TheSportsDBApi {

    fun getLastEventData(idLeague : String?) : String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventspastleague.php?id=${idLeague}"
    }

    fun getNextEventData(idLeague : String?) : String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/eventsnextleague.php?id=${idLeague}"
    }

    fun getEventDetails(id: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/lookupevent.php?id=${id}"
    }

    fun getAllLeagues(): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/all_leagues.php"
    }

    fun getAllTeams(leagueName: String?): String {
        return "${BuildConfig.BASE_URL}api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=${leagueName}"
    }

    fun getTeamDetail(idTeam : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=${idTeam}"
    }

    fun getPlayers(teamId: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.TSDB_API_KEY + "/lookup_all_players.php?id=" + teamId
    }

    fun searchTeam(teamName: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.TSDB_API_KEY + "/searchteams.php?t=" + teamName
    }

    fun searchEvent(eventName: String?): String {
        return BuildConfig.BASE_URL + "api/v1/json/" + BuildConfig.TSDB_API_KEY + "/searchevents.php?e=" + eventName
    }
}