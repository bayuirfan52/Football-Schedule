package com.bayurf.latihan.footballschedule.mvp.detail

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.mvp.main.MainActivity
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_match.*

class DetailMatchActivity : AppCompatActivity(), DetailMatchView {
    private lateinit var presenter: DetailMatchPresenter
    private lateinit var gson: Gson
    private lateinit var loadAPI: LoadAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.title = "Match Detail"

        val intentData = intent.getParcelableExtra<EventItem>(MainActivity.DETAIL_MATCH)

        gson = Gson()
        loadAPI = LoadAPI()
        presenter = DetailMatchPresenter(this,gson,loadAPI)
        presenter.getTeamBadge(intentData.idHomeTeam!!,intentData.idAwayTeam!!)

        detail_date_event.text = intentData.strDate

        //Score
        tv_detail_home_score.text = intentData.intHomeScore
        tv_detail_away_score.text = intentData.intAwayScore

        //Teams
        tv_detail_home_name.text = intentData.strHomeTeam
        tv_detail_away_name.text = intentData.strAwayTeam

        //Formations
        tv_detail_home_formation.text = intentData.strHomeFormation
        tv_detail_away_formation.text = intentData.strAwayFormation

        //Goals
        detail_home_goal_details.text = intentData.strHomeGoalDetails
        detail_away_goal_details.text = intentData.strAwayGoalDetails

        //Cards
        detail_home_red_cards.text = intentData.strHomeRedCards
        detail_home_yellow_cards.text = intentData.strHomeYellowCards

        detail_away_red_cards.text = intentData.strAwayRedCards
        detail_away_yellow_cards.text = intentData.strAwayYellowCards

        //Lineup
        detail_home_goalkeeper.text = intentData.strHomeLineupGoalkeeper
        detail_home_defense.text = intentData.strHomeLineupDefense
        detail_home_midfield.text = intentData.strHomeLineupMidfield
        detail_home_forward.text = intentData.strHomeLineupForward
        detail_home_substitute.text = intentData.strHomeLineupSubstitutes

        detail_away_goalkeeper.text = intentData.strAwayLineupGoalkeeper
        detail_away_defense.text = intentData.strAwayLineupDefense
        detail_away_midfield.text = intentData.strAwayLineupMidfield
        detail_away_forward.text = intentData.strAwayLineupForward
        detail_away_substitute.text = intentData.strAwayLineupSubstitutes
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
}
