package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.util.Log
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentClaps
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentHighlights
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentHistory
import kotlinx.android.synthetic.main.activity_view_profile.view.*

class UserProfilePagerAdapter : FragmentPagerAdapter {
    constructor(fragmentManager: FragmentManager) : super(fragmentManager) {
    }

    override fun getItem(position: Int): Fragment {
        when (position) {
            0 ->  {return FragmentHistory.getInstance()}
            1 -> {return FragmentClaps.getInstance()}
            else -> {return FragmentHighlights.getInstance()}
        }
    }

    override fun getCount(): Int {
        return 3
    }

}