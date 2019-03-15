package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

class ClapsViewModel : ViewModel {
    val currentUser : User
    val storyData:ArrayList<Stories> = ArrayList()
    private var mUserStoriesManger : UserStoriesManger
    constructor(user:User) {
        currentUser = user
        mUserStoriesManger = UserStoriesManger()
    }

    fun getClapsStories (listener:GetMultipleStories) {
        mUserStoriesManger.onGetLikedStoriesListener = listener
        mUserStoriesManger.getUserLikedStories(currentUser.Id, 3)
    }


}