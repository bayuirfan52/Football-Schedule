package com.bayurf.latihan.footballschedule.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bayurf.latihan.footballschedule.mvp.main.match.LastMatchFragment
import com.bayurf.latihan.footballschedule.mvp.main.match.NextMatchFragment

class MatchFragmentAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val pages: List<Fragment> = listOf(
        LastMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(p0: Int): Fragment {
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