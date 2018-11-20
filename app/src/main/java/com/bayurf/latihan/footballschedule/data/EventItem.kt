package com.bayurf.latihan.footballschedule.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
    val id: Int?,
    val idEvent: String?,

    val strDate : String?,

    //Team Name
    val strHomeTeam : String?,
    val strAwayTeam : String?,

    //Team ID
    val idHomeTeam : String?,
    val idAwayTeam : String?,

    //Team Score
    val intHomeScore : String?,
    val intAwayScore : String?,

    //Team Formation
    val strHomeFormation : String?,
    val strAwayFormation : String?,

    //Goal Details
    val strHomeGoalDetails : String?,
    val strAwayGoalDetails : String?,

    //Cards
    val strHomeRedCards : String?,
    val strHomeYellowCards : String?,
    val strAwayRedCards : String?,
    val strAwayYellowCards : String?,

    //Team Line up
    val strHomeLineupGoalkeeper : String?,
    val strHomeLineupDefense : String?,
    val strHomeLineupMidfield : String?,
    val strHomeLineupForward : String?,
    val strHomeLineupSubstitutes : String?,

    val strAwayLineupGoalkeeper : String?,
    val strAwayLineupDefense : String?,
    val strAwayLineupMidfield : String?,
    val strAwayLineupForward : String?,
    val strAwayLineupSubstitutes : String?
) : Parcelable {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val ID: String = "ID"
        const val ID_EVENT: String = "ID_EVENT"
        const val EVENT_DATE: String = "EVENT_DATE"
        const val HOME_TEAM: String = "HOME_TEAM"
        const val AWAY_TEAM: String = "AWAY_TEAM"
        const val HOME_ID: String = "HOME_ID"
        const val AWAY_ID: String = "AWAY_ID"
        const val HOME_SCORE: String = "HOME_SCORE"
        const val AWAY_SCORE: String = "AWAY_SCORE"
        const val HOME_FORMATION: String = "HOME_FORMATION"
        const val AWAY_FORMATION: String = "AWAY_FORMATION"
        const val HOME_GOAL_DETAILS: String = "HOME_GOAL_DETAILS"
        const val AWAY_GOAL_DETAILS: String = "AWAY_GOAL_DETAILS"
        const val HOME_RED_CARDS: String = "HOME_RED_CARDS"
        const val HOME_YELLOW_CARDS: String = "HOME_YELLOW_CARDS"
        const val AWAY_RED_CARDS: String = "AWAY_RED_CARDS"
        const val AWAY_YELLOW_CARDS: String = "AWAY_YELLOW_CARDS"
        const val HOME_LINEUP_GOALKEEPER: String = "HOME_LINEUP_GOALKEEPER"
        const val HOME_LINEUP_DEFENSE: String = "HOME_LINEUP_DEFENSE"
        const val HOME_LINEUP_MIDFIELD: String = "HOME_LINEUP_MIDFIELD"
        const val HOME_LINEUP_FORWARD: String = "HOME_LINEUP_FORWARD"
        const val HOME_LINEUP_SUBSTITUTE: String = "HOME_LINEUP_SUBSTITUTE"
        const val AWAY_LINEUP_GOALKEEPER: String = "GOALKEEPER"
        const val AWAY_LINEUP_DEFENSE: String = "AWAY_LINEUP_DEFENSE"
        const val AWAY_LINEUP_MIDFIELD: String = "AWAY_LINEUP_MIDFIELD"
        const val AWAY_LINEUP_FORWARD: String = "AWAY_LINEUP_FORWARD"
        const val AWAY_LINEUP_SUBSTITUTE: String = "AWAY_LINEUP_SUBSTITUTE"
    }
}
