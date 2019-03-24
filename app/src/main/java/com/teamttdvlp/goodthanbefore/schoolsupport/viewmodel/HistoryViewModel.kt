package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

class HistoryViewModel : ViewModel {
    private var mUserStoriesManager : UserStoriesManger
    var storyData:ArrayList<Stories> = ArrayList()

    constructor() {
        mUserStoriesManager = UserStoriesManger()
    }

    fun getHistoryStories (listener:GetMultipleStories){
        mUserStoriesManager.onGetHistorialStoriesListener = listener
        mUserStoriesManager.getUserHistorialStories(CurrentUser.currentUser!!.Id, 3)
    }
}