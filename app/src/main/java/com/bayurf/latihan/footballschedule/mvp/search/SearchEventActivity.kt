package com.bayurf.latihan.footballschedule.mvp.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.Menu
import android.widget.SearchView
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.RVMatchAdapter
import com.bayurf.latihan.footballschedule.api.LoadAPI
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.mvp.detail.match.DetailMatchActivity
import com.bayurf.latihan.footballschedule.utils.invisible
import com.bayurf.latihan.footballschedule.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search_event.*

class SearchEventActivity : AppCompatActivity(), SearchMatchView {
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var eventItem: MutableList<EventItem>
    private lateinit var adapterRV: RVMatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_event)
        supportActionBar?.title = "Search Event"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter = SearchMatchPresenter(this, Gson(), LoadAPI())
        eventItem = mutableListOf()
        adapterRV = RVMatchAdapter(this, eventItem) {
            val intent = Intent(this, DetailMatchActivity::class.java)
            intent.putExtra("id", "${it.idEvent}")
            startActivity(intent)
            finish()
        }

        pg_match_search.invisible()

        rv_match_search.adapter = adapterRV
        rv_match_search.layoutManager =
            LinearLayoutManager(this)
        presenter.getEventResult()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)

        val searchMenu = menu?.findItem(R.id.search_item)
        val view = searchMenu?.actionView as SearchView

        view.isIconified = false

        view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { presenter.getEventResult(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.toString().isEmpty()) {
                    newText?.let { presenter.getEventResult(it) }
                }
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }


    override fun showLoading() {
        rv_match_search.invisible()
        pg_match_search.visible()
    }

    override fun hideLoading() {
        pg_match_search.invisible()
        rv_match_search.visible()
    }

    override fun showQueryResult(data: List<EventItem>) {
        eventItem.clear()
        eventItem.addAll(data)
        adapterRV.notifyDataSetChanged()
        rv_match_search.scrollToPosition(0)
    }

    override fun showEmpty() {
        rv_match_search.invisible()
        Snackbar.make(pg_match_search, "Data tidak ditemukan..", Snackbar.LENGTH_SHORT).show()
    }
}
