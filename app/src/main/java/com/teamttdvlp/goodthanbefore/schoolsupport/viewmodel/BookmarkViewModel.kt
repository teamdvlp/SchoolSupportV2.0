package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

class BookmarkViewModel : ViewModel {
    val currentUser : User
    private var mUserStoriesManger : UserStoriesManger
    var storyData:ArrayList<Stories> = ArrayList()

    constructor(user:User) {
        currentUser = user
        mUserStoriesManger = UserStoriesManger()
    }

    fun getUserBookmarkStories (count:Int, listener:GetMultipleStories) {
        mUserStoriesManger.onGetBookmarkStoriesListener = listener
        mUserStoriesManger.getUserBookmarkStories(currentUser.Id, count)
    }

}