package com.bayurf.latihan.footballschedule.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.bayurf.latihan.footballschedule.mvp.main.match.LastMatchFragment
import com.bayurf.latihan.footballschedule.mvp.main.match.NextMatchFragment

class MatchFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    private val pages: List<Fragment> = listOf(
        LastMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(p0: Int): Fragment? {
        return pages[p0]
    }

    override fun getCount(): Int = pages.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "LAST"
            else -> "NEXT"
        }
    }
}