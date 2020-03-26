package com.bayurf.latihan.footballschedule.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DetailTeamFragmentAdapter(fragmentManager: FragmentManager, private val map: Map<String, Fragment>) :
    FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val title = map.keys.toList()
    private val fragment = map.values.toList()

    override fun getItem(p0: Int): Fragment = fragment[p0]

    override fun getCount(): Int = map.size

    override fun getPageTitle(position: Int): CharSequence? {
        return title[position]
    }

}