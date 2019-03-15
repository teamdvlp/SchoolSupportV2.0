package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityMainBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User

class MainViewModel(currentUser: User) : ViewModel() {
    var currentUser  = currentUser; get private set
}