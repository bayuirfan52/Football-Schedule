package com.bayurf.latihan.footballschedule.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.TeamItem
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "com.bayurf.latihan.footballschedule.data.FootballApps.db", null, 1) {

    companion object {
        private var instance: DatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseOpenHelper {
            if (instance == null) {
                instance = DatabaseOpenHelper(context.applicationContext)
            }
            return instance as DatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            EventItem.TABLE_FAVORITE, true,
            EventItem.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            EventItem.ID_EVENT to TEXT + UNIQUE,
            EventItem.EVENT_DATE to TEXT,
            EventItem.EVENT_TIME to TEXT,
            EventItem.HOME_TEAM to TEXT,
            EventItem.AWAY_TEAM to TEXT,
            EventItem.HOME_ID to TEXT,
            EventItem.AWAY_ID to TEXT,
            EventItem.HOME_SCORE to TEXT,
            EventItem.AWAY_SCORE to TEXT,
            EventItem.HOME_FORMATION to TEXT,
            EventItem.AWAY_FORMATION to TEXT,
            EventItem.HOME_GOAL_DETAILS to TEXT,
            EventItem.AWAY_GOAL_DETAILS to TEXT,
            EventItem.HOME_RED_CARDS to TEXT,
            EventItem.HOME_YELLOW_CARDS to TEXT,
            EventItem.AWAY_RED_CARDS to TEXT,
            EventItem.AWAY_YELLOW_CARDS to TEXT,
            EventItem.HOME_LINEUP_GOALKEEPER to TEXT,
            EventItem.HOME_LINEUP_DEFENSE to TEXT,
            EventItem.HOME_LINEUP_MIDFIELD to TEXT,
            EventItem.HOME_LINEUP_FORWARD to TEXT,
            EventItem.HOME_LINEUP_SUBSTITUTE to TEXT,
            EventItem.AWAY_LINEUP_GOALKEEPER to TEXT,
            EventItem.AWAY_LINEUP_DEFENSE to TEXT,
            EventItem.AWAY_LINEUP_MIDFIELD to TEXT,
            EventItem.AWAY_LINEUP_FORWARD to TEXT,
            EventItem.AWAY_LINEUP_SUBSTITUTE to TEXT
        )

        db.createTable(
            TeamItem.TABLE_TEAM, true,
            TeamItem.ID_TEAM to TEXT,
            TeamItem.TEAM_NAME to TEXT,
            TeamItem.FORMED_YEAR to TEXT,
            TeamItem.STADIUM to TEXT,
            TeamItem.TEAM_BADGE to TEXT,
            TeamItem.TEAM_OVERVIEW to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(EventItem.TABLE_FAVORITE, true)
        db.dropTable(TeamItem.TABLE_TEAM, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)