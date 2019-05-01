package com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.user.implement

import io.reactivex.Completable

interface IUserUpdater {
    fun updateUserProfile (value : Map<String, Any>, userId:String) : Completable
}