package com.bayurf.latihan.footballschedule.data

data class LeagueItem(
    val idLeague: String?,
    val strLeague: String?
) {
    override fun toString(): String {
        return strLeague.toString()
    }
}
