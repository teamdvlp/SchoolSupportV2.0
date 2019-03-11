package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetHotStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleHotStoryEvent

interface IHotStories {
     fun getHotStoriesByTopic (topic: String, to:Long, listener : GetHotStoryEvent?)
     fun getHotStoriesByTopic (topics : ArrayList<String>,to:Long, listener : GetMultipleHotStoryEvent?)
     fun setHotCheckPoint (mark:Long)
}