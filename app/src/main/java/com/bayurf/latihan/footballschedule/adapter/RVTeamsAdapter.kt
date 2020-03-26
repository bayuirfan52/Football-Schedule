package com.bayurf.latihan.footballschedule.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.data.TeamItem
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_team.*

class RVTeamsAdapter(
    private val context: Context,
    private val teamItem: List<TeamItem>,
    private val listener: (TeamItem) -> Unit
) : RecyclerView.Adapter<RVTeamsAdapter.TeamsViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TeamsViewHolder =
        TeamsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_team, p0, false))

    override fun getItemCount(): Int = teamItem.size

    override fun onBindViewHolder(p0: TeamsViewHolder, p1: Int) {
        p0.bindItem(
            teamItem[p1],
            listener
        )
    }

    class TeamsViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(teamItem: TeamItem, listener: (TeamItem) -> Unit) {
            Glide.with(containerView)
                .load(teamItem.strTeamBadge)
                .into(iv_teams)

            tv_team_name.text = teamItem.strTeam

            containerView.setOnClickListener { listener(teamItem) }
        }
    }
}