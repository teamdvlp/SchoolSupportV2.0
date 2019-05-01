package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.NewHistorialStoryEvent


interface IUserHistoryStories {
    fun setCount(count:Int)
    fun get(userId:String, listener: GetUserCompactStory)
    fun newHistorialStory (storyId:String, listener: NewHistorialStoryEvent)
}