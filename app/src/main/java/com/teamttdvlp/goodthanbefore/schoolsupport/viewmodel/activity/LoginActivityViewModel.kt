package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.teamttdvlp.goodthanbefore.schoolsupport.support.CheckRegisterInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.model.account.LoginManager
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class LoginActivityViewModel : ViewModel() {
    private var mCheck : CheckRegisterInfo =
        CheckRegisterInfo()

    private var mLoginManager : LoginManager = LoginManager()

    var isLoading : MutableLiveData<Int> = MutableLiveData()

    var onLoginEvent : LoginEvent? = null

    fun checkEmail(email:String) : Boolean  {
        return mCheck.checkEmail(email)
    }
    fun checkPassword(password:String) : Boolean  {
        return mCheck.checkPassword(password)
    }
    fun checkDisplayname(displayname:String) : Boolean  {
        return mCheck.checkDisplayName(displayname)
    }

    fun loginWithFacebook (accessToken:AccessToken) {
        mLoginManager.onLoginEvent = this.onLoginEvent
        mLoginManager.loginWithFacebook(accessToken)
    }

    fun loginWithGoogle (account:GoogleSignInAccount) {
        mLoginManager.onLoginEvent = this.onLoginEvent
        mLoginManager.loginWithGoogle(account)
    }

    fun loginNormally (email:String, password: String) {
        mLoginManager.onLoginEvent = this.onLoginEvent
        mLoginManager.loginNormally(email, password)
    }

    fun signup (email:String, password: String, displayname: String) {
        mLoginManager.onLoginEvent = this.onLoginEvent
        mLoginManager.signUp(email, password, displayname)
    }

    fun keepMeLogin () : Boolean {
        mLoginManager.onLoginEvent = this.onLoginEvent
        return mLoginManager.keepMeLogin()
    }
 }