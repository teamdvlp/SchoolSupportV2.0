package com.teamttdvlp.goodthanbefore.schoolsupport.presenter.viewmodel.activity

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.user.implementation.UserManager

class UserActivityViewModel : ViewModel {
    private var mUserManager : UserManager
    constructor() {
        mUserManager = UserManager()
    }
}