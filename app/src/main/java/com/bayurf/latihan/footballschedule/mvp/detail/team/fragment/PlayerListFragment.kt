package com.bayurf.latihan.footballschedule.mvp.detail.team.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVPlayerAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.PlayerItem
import com.bayurf.latihan.footballschedule.mvp.detail.player.DetailPlayerActivity
import com.bayurf.latihan.footballschedule.mvp.detail.player.DetailPlayerActivity.Companion.EXTRA_PARAMS
import com.bayurf.latihan.footballschedule.mvp.detail.presenter.TeamListPresenter
import com.bayurf.latihan.footballschedule.mvp.detail.view.TeamListView
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_player_list.*
import org.jetbrains.anko.bundleOf

class PlayerListFragment : Fragment(), TeamListView {
    private lateinit var presenter: TeamListPresenter
    private lateinit var listPlayer: MutableList<PlayerItem>
    private lateinit var adapterRV: RVPlayerAdapter

    companion object {
        private const val PARAM = "PARAM"

        fun newInstance(include: String): PlayerListFragment {
            val fragment = PlayerListFragment()
            fragment.arguments = bundleOf(PARAM to include)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = TeamListPresenter(this, Gson(), LoadAPI())
        listPlayer = mutableListOf()
        val idTeam = arguments?.getString(PARAM)

        context?.let {
            adapterRV = RVPlayerAdapter(it, listPlayer) {
                val intent =  Intent(context, DetailPlayerActivity::class.java)
                intent.putExtra(EXTRA_PARAMS, it)
                startActivity(intent)
            }
        }

        with(rv_player_list) {
            adapter = adapterRV
            layoutManager =
                LinearLayoutManager(context)
        }
        idTeam?.let { presenter.getPlayerList(it) }
    }

    override fun showLoading() {
        rv_player_list.invisible()
        pg_player_list.visible()
    }

    override fun hideLoading() {
        pg_player_list.invisible()
        rv_player_list.visible()
    }

    override fun showListData(data: List<PlayerItem>) {
        listPlayer.clear()
        listPlayer.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_player_list.scrollToPosition(0)
    }

    override fun showEmpty() {
        rv_player_list.invisible()
        Snackbar.make(pg_player_list, "Data tidak ditemukan..", Snackbar.LENGTH_SHORT).show()
    }
}
