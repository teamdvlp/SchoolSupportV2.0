package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentSearchByPeople
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentSearchByStories
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentSearchByTags

class SearchDirectoryViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> FragmentSearchByStories()
            1 -> FragmentSearchByPeople()
            else -> FragmentSearchByTags()
        }
    }

    override fun getCount(): Int = 3
}