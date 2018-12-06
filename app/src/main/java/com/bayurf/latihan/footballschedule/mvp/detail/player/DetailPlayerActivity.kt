package com.bayurf.latihan.footballschedule.mvp.detail.player

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.data.PlayerItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayerActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PARAMS = "EXTRA_PARAMS"
    }

    private lateinit var playerItem: PlayerItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        supportActionBar?.title = "Player Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        playerItem = intent.getParcelableExtra(EXTRA_PARAMS)
        extractData(playerItem)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun extractData(playerItem: PlayerItem) {
        Glide.with(this)
            .load(playerItem.playerBanner)
            .into(iv_player_detail)

        height.text = playerItem.strHeight
        weight.text = playerItem.strWeight
        tv_player_position_detail.text = playerItem.strPosition
        tv_player_detail.text = playerItem.strDescriptionEN

        supportActionBar?.title = playerItem.playerName
    }
}
