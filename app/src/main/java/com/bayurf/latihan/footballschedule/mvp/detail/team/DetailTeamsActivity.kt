package com.bayurf.latihan.footballschedule.mvp.detail.team

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.DetailTeamFragmentAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.helper.database
import com.bayurf.latihan.footballschedule.mvp.detail.presenter.DetailTeamPresenter
import com.bayurf.latihan.footballschedule.mvp.detail.team.fragment.OverviewFragment
import com.bayurf.latihan.footballschedule.mvp.detail.team.fragment.PlayerListFragment
import com.bayurf.latihan.footballschedule.mvp.detail.view.DetailTeamView
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamsActivity : AppCompatActivity(), DetailTeamView {
    private lateinit var presenter: DetailTeamPresenter
    private lateinit var teamItem: TeamItem
    private var menuItem: Menu? = null
    private lateinit var dataExtra: String
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_teams)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = DetailTeamPresenter(this, Gson(), LoadAPI())

        dataExtra = intent.getStringExtra("id")

        favoriteState()

        presenter.getTeamDetail(dataExtra)
    }

    private fun extract(teamItem: TeamItem) {
        Glide.with(this)
            .load(teamItem.strTeamBadge)
            .into(iv_team_detail)

        tv_team_name_detail.text = teamItem.strTeam
        tv_formed_year.text = teamItem.intFormedYear
        tv_stadium.text = teamItem.strStadium
    }

    override fun showLoading() {
        pg_team_detail.visible()
    }

    override fun hideLoading() {
        pg_team_detail.invisible()
    }

    override fun getTeamDetailData(data: List<TeamItem>) {
        teamItem = TeamItem(
            data[0].idTeam,
            data[0].strTeam,
            data[0].intFormedYear,
            data[0].strStadium,
            data[0].strTeamBadge,
            data[0].strDescriptionEN
        )
        supportActionBar?.title = teamItem.strTeam
        pagerAdapter()

        extract(teamItem)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_fav_detail_menu -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun pagerAdapter() {
        team_detail_view_pager.adapter = DetailTeamFragmentAdapter(
            supportFragmentManager,
            mapOf(
                getString(R.string.overview) to OverviewFragment.newInstance(teamItem.strDescriptionEN.toString()),
                getString(R.string.pl_detail) to PlayerListFragment.newInstance(teamItem.idTeam.toString())
            )
        )

        team_detail_tab_layout.setupWithViewPager(team_detail_view_pager)
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamItem.TABLE_TEAM)
                .whereArgs(
                    "(ID_TEAM = {idTeam})",
                    "idTeam" to dataExtra
                )
            val favorite = result.parseList(classParser<TeamItem>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    private fun addToFavorite() = try {
        database.use {
            insert(
                TeamItem.TABLE_TEAM,
                TeamItem.ID_TEAM to dataExtra,
                TeamItem.TEAM_NAME to teamItem.strTeam,
                TeamItem.FORMED_YEAR to teamItem.intFormedYear,
                TeamItem.STADIUM to teamItem.strStadium,
                TeamItem.TEAM_BADGE to teamItem.strTeamBadge,
                TeamItem.TEAM_OVERVIEW to teamItem.strDescriptionEN
            )
        }

        snackbar(pg_team_detail, "Tim berhasil ditambahkan").show()
    } catch (e: SQLiteConstraintException) {
        snackbar(pg_team_detail, e.localizedMessage).show()
    }

    private fun removeFromFavorite() = try {
        database.use {
            delete(TeamItem.TABLE_TEAM, "(ID_TEAM = {idTeam})", "idTeam" to dataExtra)

        }
        snackbar(pg_team_detail, "Data telah dihapus dari favorit").show()
    } catch (e: SQLiteConstraintException) {
        snackbar(pg_team_detail, e.localizedMessage).show()
    }

    private fun setFavorite() {
        if (isFavorite) menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_added)
        else menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_fav_add)
    }
}
