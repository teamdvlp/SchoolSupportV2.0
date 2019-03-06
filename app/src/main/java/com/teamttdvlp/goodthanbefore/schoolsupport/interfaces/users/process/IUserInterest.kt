package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent

interface IUserInterest {
    fun getUserInterest(userId:String, callback:GetUserInterestEvent)
}