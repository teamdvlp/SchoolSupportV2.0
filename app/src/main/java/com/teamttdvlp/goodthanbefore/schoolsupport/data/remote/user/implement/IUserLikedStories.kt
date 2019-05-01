package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.DeleteLikeStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.GetUserCompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.IsStoryExistInLikeListEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.otherstuff.support.dataclass.NewLikeStoryEvent


interface IUserLikedStories {
    fun setCount(count:Int)
    fun get(userId:String, listener: GetUserCompactStory)
    fun newLikedStory (storyId:String, listener:NewLikeStoryEvent)
    fun deleteLikeStory (storyId: String, listener: DeleteLikeStoryEvent)
    fun isStoryExist (storyId: String, listener: IsStoryExistInLikeListEvent)
}