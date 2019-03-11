package com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import java.lang.Exception

interface LoginEvent {
    fun onLoginSuccess (user:User)
    fun onLoginFailed (e:Exception?)
}

interface WriteInfoEvent {
    fun onWriteInfoSuccess ()
    fun onWriteInfoFailed (e:Exception?)
}

interface ReadInfoEvent {
    fun onReadInfoSuccess (user:User?)
    fun onReadInfoFailed (e:Exception?)
}

interface GetUserInterestEvent {
    fun onGetUserInterestSuccess (user:ArrayList<String>)
    fun onGetUserInterestFailed (e:Exception?)
}

interface WriteUserInterestEvent {
    fun onWriteUserInterestSuccess ()
    fun onWriteUserInterestFailed (e:Exception?)
}

