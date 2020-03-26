package com.bayurf.latihan.footballschedule.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bayurf.latihan.footballschedule.mvp.main.favorite.FavoriteMatchFragment
import com.bayurf.latihan.footballschedule.mvp.main.favorite.FavoriteTeamsFragment

class FavoriteFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages: List<Fragment> = listOf(
        FavoriteMatchFragment(),
        FavoriteTeamsFragment()
    )

    override fun getItem(p0: Int): Fragment = pages[p0]

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "MATCH"
            else -> "TEAMS"
        }
    }
}