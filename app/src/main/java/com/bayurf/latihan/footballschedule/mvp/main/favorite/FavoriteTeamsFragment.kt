package com.bayurf.latihan.footballschedule.mvp.main.favorite


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVTeamsAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.mvp.detail.team.DetailTeamsActivity
import com.bayurf.latihan.footballschedule.mvp.main.presenter.TeamPresenter
import com.bayurf.latihan.footballschedule.mvp.main.view.TeamView
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_favorite_teams.*

/**
 * A simple [Fragment] subclass.
 *
 */
class FavoriteTeamsFragment : Fragment(), TeamView {
    private lateinit var presenter: TeamPresenter
    private lateinit var adapterRV: RVTeamsAdapter
    private lateinit var teamItem: MutableList<TeamItem>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TeamPresenter(this, Gson(), LoadAPI())
        teamItem = mutableListOf()
        context?.let {
            adapterRV = RVTeamsAdapter(it, teamItem) {
                val intent =  Intent(context, DetailTeamsActivity::class.java)
                intent.putExtra("id", "${it.idTeam}")
                startActivity(intent)
            }
        }
        with(rv_fav_team) {
            adapter = adapterRV
            layoutManager =
                LinearLayoutManager(context)
        }

        presenter.showFavoriteData(context)
    }

    override fun onResume() {
        super.onResume()
        presenter.showFavoriteData(context)
    }

    override fun showLoading() {
        rv_fav_team.invisible()
    }

    override fun hideLoading() {
        rv_fav_team.visible()
    }

    override fun getTeamList(data: List<TeamItem>) {
        teamItem.clear()
        teamItem.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_fav_team.scrollToPosition(0)
    }

    override fun showLeagues(data: LeagueResponse) {}

    override fun showEmpty() {
        rv_fav_team.invisible()
        Snackbar.make(rv_fav_team, "Data tidak ditemukan..", Snackbar.LENGTH_SHORT).show()
    }
}