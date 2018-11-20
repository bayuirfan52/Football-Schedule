package com.bayurf.latihan.footballschedule.mvp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVMatchAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.mvp.detail.DetailMatchActivity
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter : RVMatchAdapter
    private var eventItem : MutableList<EventItem> = mutableListOf()
    private var menuPosition: Boolean = true
    private val league: String = "4328"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val gson = Gson()
        val loadAPI = LoadAPI()
        presenter = MainPresenter(this, gson, loadAPI)
        rv_match.layoutManager = LinearLayoutManager(this)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.last_match -> {
                    presenter.showLastEventData(league)
                    menuPosition = true
                }

                R.id.next_match -> {
                    presenter.showNextEventData(league)
                    menuPosition = true
                }
                R.id.favorites -> {
                    presenter.showFavoriteData(this)
                    menuPosition = false
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.last_match
        adapter = RVMatchAdapter(this,eventItem){
            startActivity<DetailMatchActivity>("id" to "${it.idEvent}")
        }
        rv_match.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        if (!menuPosition) presenter.showFavoriteData(this)
    }

    override fun showLoading() {
        pg_loading_progress_bar.visible()
        rv_match.invisible()
    }

    override fun hideLoading() {
        pg_loading_progress_bar.invisible()
        rv_match.visible()
    }

    override fun showMatch(data: List<EventItem>) {
        showEvent(data)
    }

    override fun showEmpty() {
        rv_match.invisible()
        Toast.makeText(this, "Data kosong", Toast.LENGTH_SHORT).show()
    }

    override fun showFavorite(data: List<EventItem>) {
        showEvent(data)
    }

    private fun showEvent(data : List<EventItem>){
        rv_match.visible()
        eventItem.clear()
        eventItem.addAll(data)
        adapter.notifyDataSetChanged()
    }
}