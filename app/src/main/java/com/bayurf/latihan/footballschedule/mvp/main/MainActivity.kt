package com.bayurf.latihan.footballschedule.mvp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.mvp.main.favorite.SelectFavoriteFragment
import com.bayurf.latihan.footballschedule.mvp.main.match.MatchFragments
import com.bayurf.latihan.footballschedule.mvp.main.teams.TeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.match -> {
                    setFragment(MatchFragments(), savedInstanceState)
                }
                R.id.teams -> {
                    setFragment(TeamsFragment(), savedInstanceState)
                }
                R.id.favorites -> {
                    setFragment(SelectFavoriteFragment(), savedInstanceState)
                }
            }
            true
        }
        bottom_navigation.selectedItemId = R.id.match
    }

    private fun setFragment(fragment: Fragment?, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            fragment?.let {
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.frame_layout, it)
                    .commit()
            }
        }
    }
}