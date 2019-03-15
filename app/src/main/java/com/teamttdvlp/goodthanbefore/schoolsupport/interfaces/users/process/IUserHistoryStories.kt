package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserCompactStory


interface IUserHistoryStories {
    fun setCount(count:Int)
    fun get(userId:String, listener: GetUserCompactStory)
}