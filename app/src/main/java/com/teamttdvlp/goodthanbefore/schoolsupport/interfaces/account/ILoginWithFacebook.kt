package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.facebook.AccessToken

interface ILoginWithFacebook {
    fun login (token : AccessToken)
}