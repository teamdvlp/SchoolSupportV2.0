package com.teamttdvlp.goodthanbefore.schoolsupport.presenter.viewmodel.fragment

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.story.implementation.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.GetMultipleStories

class ClapsFragmentViewModel : ViewModel {
    val storyData:ArrayList<Stories> = ArrayList()
    private var mUserStoriesManger : UserStoriesManger
    constructor() {
        mUserStoriesManger =
            UserStoriesManger()
    }

    fun getClapsStories (listener:GetMultipleStories) {
        mUserStoriesManger.onGetLikedStoriesListener = listener
        mUserStoriesManger.getUserLikedStories(CurrentUser.currentUser!!.Id, 3)
    }


}