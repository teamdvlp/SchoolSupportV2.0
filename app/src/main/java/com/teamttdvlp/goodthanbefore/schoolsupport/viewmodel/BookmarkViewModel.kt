package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories

class BookmarkViewModel : ViewModel {
    private var mUserStoriesManger : UserStoriesManger
    var storyData:ArrayList<Stories> = ArrayList()

    constructor() {
        mUserStoriesManger = UserStoriesManger()
    }

    fun getUserBookmarkStories (count:Int, listener:GetMultipleStories) {
        mUserStoriesManger.onGetBookmarkStoriesListener = listener
        mUserStoriesManger.getUserBookmarkStories(CurrentUser.currentUser!!.Id, count)
    }

}