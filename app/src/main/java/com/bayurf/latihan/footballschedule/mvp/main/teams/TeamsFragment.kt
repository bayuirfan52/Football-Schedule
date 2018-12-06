package com.bayurf.latihan.footballschedule.mvp.main.teams


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVTeamsAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.LeagueItem
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bayurf.latihan.footballschedule.mvp.detail.team.DetailTeamsActivity
import com.bayurf.latihan.footballschedule.mvp.main.presenter.TeamPresenter
import com.bayurf.latihan.footballschedule.mvp.main.view.TeamView
import com.bayurf.latihan.footballschedule.utils.collectingArrayData
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class TeamsFragment : Fragment(), TeamView {

    private lateinit var presenter: TeamPresenter
    private lateinit var leagueItem: LeagueItem
    private lateinit var teamItem: MutableList<TeamItem>
    private lateinit var adapterRV: RVTeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        presenter = TeamPresenter(this, Gson(), LoadAPI())
        teamItem = mutableListOf()

        context?.let {
            teams_spinner.collectingArrayData(it)
            adapterRV = RVTeamsAdapter(it, teamItem) {
                startActivity<DetailTeamsActivity>("id" to "${it.idTeam}")
            }
        }
        with(activity as AppCompatActivity) {
            supportActionBar?.title = "Football Apps - Team"
        }
        with(rv_teams) {
            adapter = adapterRV
            layoutManager = LinearLayoutManager(context)
        }

        presenter.getAllLeagues()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.search_menu, menu)

        val searchMenu = menu?.findItem(R.id.search_item)
        val view = searchMenu?.actionView as SearchView

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.getTeamResult(it) }

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isEmpty()) {
                    teams_spinner.visible()
                    presenter.getAllTeams(teams_spinner.selectedItem.toString())
                } else teams_spinner.invisible()

                return true
            }

        })
    }

    override fun showLoading() {
        rv_teams.invisible()
        pg_teams.visible()
    }

    override fun hideLoading() {
        pg_teams.invisible()
        rv_teams.visible()
    }

    override fun getTeamList(data: List<TeamItem>) {
        showTeamList(data)
    }

    override fun showLeagues(data: LeagueResponse) {
        context?.let {
            teams_spinner.adapter = ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, data.leagues)
        }

        teams_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueItem = teams_spinner.selectedItem as LeagueItem

                leagueItem.strLeague?.let { presenter.getAllTeams(it.replace(" ", "%20")) }
            }
        }

    }

    override fun showEmpty() {
        rv_teams.invisible()
        snackbar(pg_teams, "Data tidak ditemukan..").show()
    }

    private fun showTeamList(data: List<TeamItem>) {
        teamItem.clear()
        teamItem.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_teams.scrollToPosition(0)
    }
}
