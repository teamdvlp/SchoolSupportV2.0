package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import androidx.lifecycle.ViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CheckRegisterInfo

class LoginViewModel : ViewModel() {
    private var mCheck :CheckRegisterInfo = CheckRegisterInfo()
    fun checkEmail(email:String) : Boolean  {
        return mCheck.checkEmail(email)
    }
    fun checkPassword(password:String) : Boolean  {
        return mCheck.checkPassword(password)
    }
    fun checkDisplayname(displayname:String) : Boolean  {
        return mCheck.checkDisplayName(displayname)
    }
}