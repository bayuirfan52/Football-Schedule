package com.bayurf.latihan.footballschedule.mvp.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVMatchAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.data.League
import com.bayurf.latihan.footballschedule.data.LeagueResponse
import com.bayurf.latihan.footballschedule.mvp.detail.DetailMatchActivity
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenter: MainPresenter
    private lateinit var adapter : RVMatchAdapter
    private lateinit var leagueItem : League
    private var eventItem : MutableList<EventItem> = mutableListOf()


    companion object {
        const val DETAIL_MATCH = "DETAIL_MATCH"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val gson = Gson()
        val loadAPI = LoadAPI()
        presenter = MainPresenter(this, gson, loadAPI)
        presenter.getAllLeague()
        adapter = RVMatchAdapter(this,eventItem){
                eventItem : EventItem -> itemClick(eventItem) }
        rv_match.layoutManager = LinearLayoutManager(this)
        rv_match.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.match_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item!!.itemId){
            R.id.last_match -> presenter.showLastEventData(leagueItem.idLeague!!)
            else -> presenter.showNextEventData(leagueItem.idLeague!!)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun getListLeagueData(leagueData: LeagueResponse) {
        select_league.adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,leagueData.leagues)
        
        select_league.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                leagueItem = select_league.selectedItem as League
                when(presenter.section){
                    1 -> presenter.showLastEventData(leagueItem.idLeague!!)
                    else -> presenter.showNextEventData(leagueItem.idLeague!!)
                }
            }

        }
    }

    override fun showLoading() {
        pg_loading_progress_bar.visible()
        rv_match.invisible()
    }

    override fun hideLoading() {
        pg_loading_progress_bar.invisible()
        rv_match.visible()
    }

    override fun showLastEventData(data : List<EventItem>) {
        showEvent(data)
    }

    override fun showNextEventData(data : List<EventItem>) {
        showEvent(data)
    }

    private fun itemClick(eventItem: EventItem){
        startActivity<DetailMatchActivity>(DETAIL_MATCH to eventItem)
    }

    private fun showEvent(data : List<EventItem>){
        eventItem.clear()
        eventItem.addAll(data)
        adapter.notifyDataSetChanged()
    }
}