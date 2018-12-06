package com.bayurf.latihan.footballschedule.mvp.detail.team.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bayurf.latihan.footballschedule.R
import kotlinx.android.synthetic.main.fragment_overview.*
import org.jetbrains.anko.bundleOf

class OverviewFragment : Fragment() {

    companion object {
        private const val PARAM = "PARAM"

        fun newInstance(include: String): OverviewFragment {
            val fragment = OverviewFragment()
            fragment.arguments = bundleOf(PARAM to include)

            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tv_overview.text = arguments?.getString(PARAM)
    }


}
