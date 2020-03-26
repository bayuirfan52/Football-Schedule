package com.bayurf.latihan.footballschedule.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.data.PlayerItem
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item_player.*

class RVPlayerAdapter(
    private val context: Context,
    private val playerItem: List<PlayerItem>,
    private val listener: (PlayerItem) -> Unit
) : RecyclerView.Adapter<RVPlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item_player, p0, false))

    override fun getItemCount(): Int = playerItem.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(playerItem[p1], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(playerItem: PlayerItem, listener: (PlayerItem) -> Unit) {
            Glide.with(containerView)
                .load(playerItem.playerIcon)
                .into(iv_players)

            tv_player_name.text = playerItem.playerName
            tv_player_position.text = playerItem.strPosition

            containerView.setOnClickListener { listener(playerItem) }
        }
    }
}