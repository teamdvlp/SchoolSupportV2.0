package com.teamttdvlp.goodthanbefore.schoolsupport.presenter.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.story.implementation.SearchStoriesManager
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.SearchStoriesEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.presenter.view.activity.SearchActivity

/**
 * @see SearchActivity
 */
class SearchActivityViewModel : ViewModel () {

    val searchManager =
        SearchStoriesManager()

    var resultStories = MutableLiveData<ArrayList<Stories>>()

    fun searchStoriesByTitle (keyword : String, callBack : SearchStoriesEvent) {
        searchManager.searchStoriesByTitle (keyword, callBack)
    }

}