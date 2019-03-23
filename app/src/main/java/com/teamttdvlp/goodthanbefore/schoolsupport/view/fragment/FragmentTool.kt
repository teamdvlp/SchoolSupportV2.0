package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.BangTuanHoanActivity
import kotlinx.android.synthetic.main.fragment_tool.*


class FragmentTool : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tool, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        btn_bth.setOnClickListener {
            startActivity(Intent(activity!!, BangTuanHoanActivity::class.java))
        }
    }

}
