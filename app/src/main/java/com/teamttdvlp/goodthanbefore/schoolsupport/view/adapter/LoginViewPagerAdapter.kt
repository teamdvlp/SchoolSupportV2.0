package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentLogin
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentSignUp

class LoginViewPagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
            if (position == 0) {
                return FragmentLogin.getInstance()
            } else {
                return FragmentSignUp.getInstance()
            }
    }

    override fun getCount(): Int {
        return 2
    }

}