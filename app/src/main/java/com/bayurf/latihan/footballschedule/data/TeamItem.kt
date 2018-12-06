package com.bayurf.latihan.footballschedule.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamItem(
    val idTeam: String?,
    val strTeam: String?,
    val intFormedYear: String,
    val strStadium: String?,
    val strTeamBadge: String?,
    val strDescriptionEN: String?
) : Parcelable {
    companion object {
        const val TABLE_TEAM: String = "TABLE_TEAM"
        const val ID_TEAM: String = "ID_TEAM"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val FORMED_YEAR: String = "FORMED_YEAR"
        const val STADIUM: String = "STADIUM"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_OVERVIEW: String = "TEAM_OVERVIEW"
    }
}
