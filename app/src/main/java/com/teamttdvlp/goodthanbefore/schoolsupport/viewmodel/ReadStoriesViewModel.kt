package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoriesActivity

/**
 * @see ReadStoriesActivity
 */
class ReadStoriesViewModel : ViewModel  {
    var story : Stories
    private var mUserManager : UserManager
    constructor() {
        story = Stories()
        mUserManager = UserManager()
    }

    fun getAuthor (authorId:String, listener : ReadInfoEvent) {
        mUserManager.getUserInfoListener = listener
        mUserManager.getInfo(authorId)
    }

}