package com.bayurf.latihan.footballschedule.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.bayurf.latihan.footballschedule.data.EventItem
import org.jetbrains.anko.db.*

class DatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "com.bayurf.latihan.footballschedule.data.EventItemEvent.db", null, 1) {

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
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(EventItem.TABLE_FAVORITE, true)
    }
}

val Context.database: DatabaseOpenHelper
    get() = DatabaseOpenHelper.getInstance(applicationContext)