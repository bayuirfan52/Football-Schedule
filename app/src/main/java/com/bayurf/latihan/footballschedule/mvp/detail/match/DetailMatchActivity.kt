package com.bayurf.latihan.footballschedule.mvp.detail.match

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.helper.database
import com.bayurf.latihan.footballschedule.mvp.detail.presenter.DetailMatchPresenter
import com.bayurf.latihan.footballschedule.mvp.detail.view.DetailMatchView
import com.bayurf.latihan.footballschedule.utils.FormatDate
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {

    private lateinit var presenter: DetailMatchPresenter
    private lateinit var gson: Gson
    private lateinit var eventData: EventItem
    private lateinit var loadAPI: LoadAPI
    private var intentData: String? = ""
    private lateinit var progressBar: ProgressBar
    private var isEventItem: Boolean = false
    private var menuItem: Menu? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        progressBar = findViewById(R.id.detail_progress_bar)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        intentData = intent.getStringExtra("id")

        favoriteState()
        gson = Gson()
        loadAPI = LoadAPI()
        presenter = DetailMatchPresenter(this, gson, loadAPI)
        intentData?.let { presenter.getEventDetail(it) }
    }

    override fun showLoading() {
        detail_progress_bar.visible()
    }

    override fun hideLoading() {
        detail_progress_bar.invisible()
    }

    override fun showImageBadgeExtra(idHomeTeam: List<TeamItem>, idAwayTeam: List<TeamItem>) {
        Glide.with(this)
            .load(idHomeTeam[0].strTeamBadge)
            .into(detail_image_home_badge)

        Glide.with(this)
            .load(idAwayTeam[0].strTeamBadge)
            .into(detail_image_away_badge)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setEventItem()
        return true
    }

    override fun showDetailData(data: List<EventItem>) {
        eventData = EventItem(
            data[0].id,
            data[0].idEvent,
            data[0].dateEvent,
            data[0].strTime,
            data[0].strHomeTeam,
            data[0].strAwayTeam,
            data[0].idHomeTeam,
            data[0].idAwayTeam,
            data[0].intHomeScore,
            data[0].intAwayScore,
            data[0].strHomeFormation,
            data[0].strAwayFormation,
            data[0].strHomeGoalDetails,
            data[0].strAwayGoalDetails,
            data[0].strHomeRedCards,
            data[0].strHomeYellowCards,
            data[0].strAwayRedCards,
            data[0].strAwayYellowCards,
            data[0].strHomeLineupGoalkeeper,
            data[0].strHomeLineupDefense,
            data[0].strHomeLineupMidfield,
            data[0].strHomeLineupForward,
            data[0].strHomeLineupSubstitutes,
            data[0].strAwayLineupGoalkeeper,
            data[0].strAwayLineupDefense,
            data[0].strAwayLineupMidfield,
            data[0].strAwayLineupForward,
            data[0].strAwayLineupSubstitutes
        )

        data[0].idHomeTeam?.let { data[0].idAwayTeam?.let { it1 -> presenter.getTeamBadge(it, it1) } }

        detail_date_event.text = FormatDate.reformatDate(eventData.dateEvent)
        detail_time_event.text = FormatDate.reformatTime(eventData.strTime)
        //Score
        tv_detail_home_score.text = eventData.intHomeScore
        tv_detail_away_score.text = eventData.intAwayScore
        //Teams
        tv_detail_home_name.text = eventData.strHomeTeam
        tv_detail_away_name.text = eventData.strAwayTeam
        //Formations
        tv_detail_home_formation.text = eventData.strHomeFormation
        tv_detail_away_formation.text = eventData.strAwayFormation
        //Goals
        detail_home_goal_details.text = eventData.strHomeGoalDetails
        detail_away_goal_details.text = eventData.strAwayGoalDetails
        //Cards
        detail_home_red_cards.text = eventData.strHomeRedCards
        detail_home_yellow_cards.text = eventData.strHomeYellowCards
        detail_away_red_cards.text = eventData.strAwayRedCards
        detail_away_yellow_cards.text = eventData.strAwayYellowCards
        //Lineup
        detail_home_goalkeeper.text = eventData.strHomeLineupGoalkeeper
        detail_home_defense.text = eventData.strHomeLineupDefense
        detail_home_midfield.text = eventData.strHomeLineupMidfield
        detail_home_forward.text = eventData.strHomeLineupForward
        detail_home_substitute.text = eventData.strHomeLineupSubstitutes
        detail_away_goalkeeper.text = eventData.strAwayLineupGoalkeeper
        detail_away_defense.text = eventData.strAwayLineupDefense
        detail_away_midfield.text = eventData.strAwayLineupMidfield
        detail_away_forward.text = eventData.strAwayLineupForward
        detail_away_substitute.text = eventData.strAwayLineupSubstitutes

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_fav_detail_menu -> {
                if (isEventItem) removeFromEventItem() else addToEventItem()
                isEventItem = !isEventItem
                setEventItem()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(EventItem.TABLE_FAVORITE)
                .whereArgs(
                    "(ID_EVENT = {idEvent})",
                    "idEvent" to intentData!!
                )
            val favorite = result.parseList(classParser<EventItem>())
            if (favorite.isNotEmpty()) isEventItem = true
        }
    }

    private fun addToEventItem() = try {
        database.use {
            insert(
                EventItem.TABLE_FAVORITE,
                EventItem.ID_EVENT to intentData,
                EventItem.EVENT_DATE to eventData.dateEvent,
                EventItem.EVENT_TIME to eventData.strTime,
                EventItem.HOME_TEAM to eventData.strHomeTeam,
                EventItem.AWAY_TEAM to eventData.strAwayTeam,
                EventItem.HOME_SCORE to eventData.intHomeScore,
                EventItem.HOME_ID to eventData.idHomeTeam,
                EventItem.AWAY_ID to eventData.idAwayTeam,
                EventItem.HOME_SCORE to eventData.intHomeScore,
                EventItem.AWAY_SCORE to eventData.intAwayScore,
                EventItem.HOME_FORMATION to eventData.strHomeFormation,
                EventItem.AWAY_FORMATION to eventData.strAwayFormation,
                EventItem.HOME_GOAL_DETAILS to eventData.strHomeGoalDetails,
                EventItem.AWAY_GOAL_DETAILS to eventData.strAwayGoalDetails,
                EventItem.HOME_RED_CARDS to eventData.strHomeRedCards,
                EventItem.HOME_YELLOW_CARDS to eventData.strAwayYellowCards,
                EventItem.AWAY_RED_CARDS to eventData.strAwayRedCards,
                EventItem.AWAY_YELLOW_CARDS to eventData.strAwayYellowCards,
                EventItem.HOME_LINEUP_GOALKEEPER to eventData.strHomeLineupGoalkeeper,
                EventItem.HOME_LINEUP_DEFENSE to eventData.strHomeLineupDefense,
                EventItem.HOME_LINEUP_MIDFIELD to eventData.strHomeLineupMidfield,
                EventItem.HOME_LINEUP_FORWARD to eventData.strHomeLineupForward,
                EventItem.HOME_LINEUP_SUBSTITUTE to eventData.strHomeLineupSubstitutes,
                EventItem.AWAY_LINEUP_GOALKEEPER to eventData.strAwayLineupGoalkeeper,
                EventItem.AWAY_LINEUP_DEFENSE to eventData.strAwayLineupDefense,
                EventItem.AWAY_LINEUP_MIDFIELD to eventData.strAwayLineupMidfield,
                EventItem.AWAY_LINEUP_FORWARD to eventData.strAwayLineupForward,
                EventItem.AWAY_LINEUP_SUBSTITUTE to eventData.strAwayLineupSubstitutes
            )
        }
        Snackbar.make(progressBar, "Event telah ditambahkan ke Favorit", Snackbar.LENGTH_SHORT).show()
    } catch (e: SQLiteConstraintException) {
        Snackbar.make(progressBar, e.localizedMessage!!, Snackbar.LENGTH_SHORT).show()
    }

    private fun removeFromEventItem() {
        try {
            database.use {
                delete(EventItem.TABLE_FAVORITE, "(ID_EVENT = {idEvent})", "idEvent" to intentData!!)
            }
            Snackbar.make(progressBar, "Event telah dihapus dari Favorit", Snackbar.LENGTH_SHORT).show()
        } catch (e: SQLiteConstraintException) {
            Snackbar.make(progressBar, e.localizedMessage!!, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun setEventItem() {
        if (isEventItem) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_added)
        } else {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_add)
        }
    }
}
