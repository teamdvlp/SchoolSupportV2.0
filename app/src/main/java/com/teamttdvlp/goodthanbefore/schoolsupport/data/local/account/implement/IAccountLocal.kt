package com.teamttdvlp.goodthanbefore.schoolsupport.data.local.account.implement

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

interface IAccountLocal {
    fun keepMeLogin () : Single<User>
}