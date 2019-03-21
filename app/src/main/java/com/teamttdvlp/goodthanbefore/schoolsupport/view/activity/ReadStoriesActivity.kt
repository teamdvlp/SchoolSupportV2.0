package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityReadStoriesBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ReadStoriesViewModel

class ReadStoriesActivity : AppCompatActivity() {

    lateinit var mBinding : ActivityReadStoriesBinding

    lateinit var mViewModel : ReadStoriesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_stories)
        setUp()
        addControls()
        addEvents()
    }

    private fun setUp () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_read_stories)
        mViewModel = getViewModel()
    }

    private fun addControls() {

    }

    private fun addEvents() {

    }
}
