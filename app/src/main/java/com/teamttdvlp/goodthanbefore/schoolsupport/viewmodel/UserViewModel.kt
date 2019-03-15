package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import java.lang.Exception

class UserViewModel : ViewModel {
    private var mUserManager : UserManager
    var currentUser:User; get private set
    constructor(user:User) {
        currentUser = user
        mUserManager = UserManager()
    }
}