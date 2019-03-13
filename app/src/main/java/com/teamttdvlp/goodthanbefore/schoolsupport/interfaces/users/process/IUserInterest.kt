package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent
import java.lang.Exception

interface IUserInterest {
    fun getUserInterest(userId:String, callback:GetUserInterestEvent)
}

