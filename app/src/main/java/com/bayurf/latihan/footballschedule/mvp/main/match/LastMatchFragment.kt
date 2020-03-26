package com.bayurf.latihan.footballschedule.mvp.main.match


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVMatchAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.LeagueItem
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.mvp.detail.match.DetailMatchActivity
import com.bayurf.latihan.footballschedule.mvp.main.presenter.MatchPresenter
import com.bayurf.latihan.footballschedule.mvp.main.view.MatchView
import com.bayurf.latihan.footballschedule.utils.collectingArrayData
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_last_match.*

/**
 * A simple [Fragment] subclass.
 *
 */
class LastMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: MatchPresenter
    private lateinit var leagueItem: LeagueItem
    private lateinit var eventItem: MutableList<EventItem>
    private lateinit var adapterRV: RVMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = MatchPresenter(this, Gson(), LoadAPI())
        eventItem = mutableListOf()

        context?.let {
            last_match_spinner.collectingArrayData(it)
            adapterRV = RVMatchAdapter(it, eventItem) {
                val intent =  Intent(context, DetailMatchActivity::class.java)
                intent.putExtra("id", "${it.idEvent}")
                startActivity(intent)
            }
        }

        with(rv_last_match) {
            adapter = adapterRV
            layoutManager =
                LinearLayoutManager(context)
        }

        presenter.getAllLeagues()
    }

    override fun showLoading() {
        rv_last_match.invisible()
        pg_last_match.visible()
    }

    override fun hideLoading() {
        rv_last_match.visible()
        pg_last_match.invisible()
    }

    override fun showMatch(data: List<EventItem>) {
        showEvent(data)
    }

    override fun showLeagues(data: LeagueResponse) {
        context?.let {
            last_match_spinner.adapter = ArrayAdapter(it, android.R.layout.simple_spinner_dropdown_item, data.leagues)
        }

        last_match_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueItem = last_match_spinner.selectedItem as LeagueItem
                leagueItem.idLeague?.let { presenter.showLastEventData(it) }
            }

        }
    }

    override fun showEmpty() {
        rv_last_match.invisible()
        Snackbar.make(pg_last_match, "Data tidak ditemukan..", Snackbar.LENGTH_SHORT).show()
    }

    private fun showEvent(data: List<EventItem>) {
        eventItem.clear()
        eventItem.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_last_match.scrollToPosition(0)
    }
}
