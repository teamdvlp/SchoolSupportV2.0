package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.CreateStoriesActivity

/**
 * @see WriteStoriesActivity
 */
class WriteStoriesViewModel : ViewModel() {
    lateinit var activityResultListener: CreateStoriesActivity.OnActivityResultListener
}