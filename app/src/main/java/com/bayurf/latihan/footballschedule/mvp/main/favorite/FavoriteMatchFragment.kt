package com.bayurf.latihan.footballschedule.mvp.main.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVMatchAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.mvp.detail.match.DetailMatchActivity
import com.bayurf.latihan.footballschedule.mvp.main.presenter.MatchPresenter
import com.bayurf.latihan.footballschedule.mvp.main.view.MatchView
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_match_favorite.*

class FavoriteMatchFragment : Fragment(), MatchView {

    private lateinit var presenter: MatchPresenter
    private lateinit var eventItem: MutableList<EventItem>
    private lateinit var adapterRV: RVMatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = MatchPresenter(this, Gson(), LoadAPI())
        eventItem = mutableListOf()
        context?.let {
            adapterRV = RVMatchAdapter(it, eventItem) {it1 ->
                val intent = Intent(it, DetailMatchActivity::class.java)
                intent.putExtra("id", "${it1.idEvent}")
                startActivity(intent)
            }
        }

        with(rv_fav_match) {
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
        rv_fav_match.invisible()
    }

    override fun hideLoading() {
        rv_fav_match.visible()
    }

    override fun showMatch(data: List<EventItem>) {
        eventItem.clear()
        eventItem.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_fav_match.scrollToPosition(0)
    }

    override fun showLeagues(data: LeagueResponse) {}

    override fun showEmpty() {
        rv_fav_match.invisible()
        Snackbar.make(rv_fav_match, "Data tidak ditemukan..", Snackbar.LENGTH_SHORT).show()
    }
}
