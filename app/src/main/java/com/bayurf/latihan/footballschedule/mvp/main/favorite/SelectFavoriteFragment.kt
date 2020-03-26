package com.bayurf.latihan.footballschedule.mvp.main.favorite


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.FavoriteFragmentAdapter
import kotlinx.android.synthetic.main.fragment_select_favorite.*

class SelectFavoriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity as AppCompatActivity) {
            supportActionBar?.title = "Football Apps - Favorite"

            favorite_view_pager.adapter = FavoriteFragmentAdapter(supportFragmentManager)

            favorite_tab_layout.setupWithViewPager(favorite_view_pager)
        }
    }

}
