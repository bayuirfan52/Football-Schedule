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

    fun getTeamDetail(idTeam : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=${idTeam}"
    }
}