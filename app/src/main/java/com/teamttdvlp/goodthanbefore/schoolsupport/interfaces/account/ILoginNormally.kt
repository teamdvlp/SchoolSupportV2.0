package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

interface ILoginNormally {
    fun login (email:String, password:String, callback:LoginEvent)
}