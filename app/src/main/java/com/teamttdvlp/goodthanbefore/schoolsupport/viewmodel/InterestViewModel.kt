package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IInterestDownloadManager
import com.teamttdvlp.goodthanbefore.schoolsupport.model.InterestDownloadManager
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity
import java.lang.Exception

const val ONE_MEGABYTE : Long = 1024 * 1024

class InterestViewModel (var app : Application): AndroidViewModel (app) {

    var interest_list_ld = MutableLiveData<ArrayList<Interest>>()

    var ld = MutableLiveData<Drawable>()

    var dataReceiver = ArrayList<Interest>()

    var mInterestSetUpManager : IInterestDownloadManager = InterestDownloadManager(app)

    var mUserInfoManager = UserInfo()

    fun loadData (onAnImageLoadSuccess : (interst : Interest) -> Unit, onLoadFailed: (Exception) -> Unit) {
        mInterestSetUpManager.loadInterest(onAnImageLoadSuccess, onLoadFailed)
    }

    fun writeUserToFS(user: User, callback : WriteInfoEvent) {
        mUserInfoManager.writeInfo(user, callback)
    }

}