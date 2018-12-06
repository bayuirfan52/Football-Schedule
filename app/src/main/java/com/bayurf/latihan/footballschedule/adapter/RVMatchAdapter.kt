package com.bayurf.latihan.footballschedule.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.data.EventItem
import com.bayurf.latihan.footballschedule.utils.FormatDate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_match.*

class RVMatchAdapter(
    private val context: Context,
    private val eventItem: List<EventItem>,
    private val listener: (EventItem) -> Unit
) : RecyclerView.Adapter<RVMatchAdapter.LastMatchViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): LastMatchViewHolder =
        LastMatchViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_match, viewGroup, false))

    override fun getItemCount(): Int = eventItem.size

    override fun onBindViewHolder(lastMatchViewHolder: LastMatchViewHolder, position: Int) {
        lastMatchViewHolder.bindItem(eventItem[position], listener)
    }

    class LastMatchViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bindItem(eventItem : EventItem, listener: (EventItem) -> Unit){
            last_date_event.text = FormatDate.reformatDate(eventItem.dateEvent)
            last_time_event.text = FormatDate.reformatTime(eventItem.strTime)

            tv_last_home_team.text = eventItem.strHomeTeam
            tv_last_away_team.text = eventItem.strAwayTeam

            tv_last_home_score.text = eventItem.intHomeScore
            tv_last_away_score.text = eventItem.intAwayScore

            containerView.setOnClickListener { listener(eventItem) }
        }
    }
}