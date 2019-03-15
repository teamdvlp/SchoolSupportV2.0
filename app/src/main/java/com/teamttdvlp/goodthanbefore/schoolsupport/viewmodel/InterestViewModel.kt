package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.IInterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.InterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserInterest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.SetUserInterestEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import java.lang.Exception

const val ONE_MEGABYTE : Long = 1024 * 1024

class InterestViewModel (var app : Application): AndroidViewModel (app) {

    var interest_list_ld = MutableLiveData<ArrayList<Interest>>()

    var ld = MutableLiveData<Drawable>()

    var dataReceiver = ArrayList<Interest>()

    var mInterestSetUpManager : IInterestDownload = InterestDownload(app)

    var mUserInterest = UserInterest()

    fun loadData (onLoadSuccess : (interst : ArrayList<Interest>) -> Unit, onLoadFailed: (Exception) -> Unit) {
        mInterestSetUpManager.loadInterest(onLoadSuccess, onLoadFailed)
    }

    fun setUserInterest (userId : String, interests:ArrayList<String>, callback : SetUserInterestEvent) {
        mUserInterest.setUserInterest(userId,interests, callback)
    }

}