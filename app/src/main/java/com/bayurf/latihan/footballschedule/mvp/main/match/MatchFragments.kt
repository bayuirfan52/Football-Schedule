package com.bayurf.latihan.footballschedule.mvp.main.match


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import com.bayurf.latihan.footballschedule.R
import com.bayurf.latihan.footballschedule.adapter.MatchFragmentAdapter
import com.bayurf.latihan.footballschedule.mvp.search.SearchEventActivity
import kotlinx.android.synthetic.main.fragment_match.*
import org.jetbrains.anko.startActivity

/**
 * A simple [Fragment] subclass.
 *
 */
class MatchFragments : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        with(activity as AppCompatActivity) {
            supportActionBar?.title = "Football Apps - MATCH"
            match_view_pager.adapter = MatchFragmentAdapter(supportFragmentManager)

            match_tab_layout.setupWithViewPager(match_view_pager)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater?.inflate(R.menu.match_search, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.match_search -> {
                context?.startActivity<SearchEventActivity>()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}
