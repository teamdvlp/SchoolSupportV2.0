package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User

class ViewProfileViewModel : ViewModel {
    var currentUser : User; get private set
    constructor(user:User) {
        currentUser = user
    }
}