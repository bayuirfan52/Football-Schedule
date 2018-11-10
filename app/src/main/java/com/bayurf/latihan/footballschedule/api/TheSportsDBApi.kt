package com.bayurf.latihan.footballschedule.api

import android.net.Uri
import com.bayurf.latihan.footballschedule.BuildConfig

object TheSportsDBApi {
    fun getAllLeagues() : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/all_leagues.php"
    }

    fun getLastEventData(idLeague : String?) : String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", idLeague)
            .build()
            .toString()
    }

    fun getNextEventData(idLeague : String?) : String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", idLeague)
            .build()
            .toString()
    }

    fun getTeamDetail(idTeam : String?) : String{
        return BuildConfig.BASE_URL + "api/v1/json/${BuildConfig.TSDB_API_KEY}" + "/lookupteam.php?id=${idTeam!!}"
    }
}