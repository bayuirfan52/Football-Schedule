package com.bayurf.latihan.footballschedule.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventItem(
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
) : Parcelable
