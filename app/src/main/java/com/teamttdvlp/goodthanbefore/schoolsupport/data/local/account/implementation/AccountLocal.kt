package com.teamttdvlp.goodthanbefore.schoolsupport.data.local.account.implementation

import android.util.Log
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.data.local.account.implement.IAccountLocal
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single
import java.lang.Exception

class AccountLocal : IAccountLocal {

    private var mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun keepMeLogin(): Single<User> {
        val result : Single<User> = Single.create {
            if (mAuth.currentUser != null) {
                var user = User()
                user.Id = mAuth.currentUser!!.uid
                user.DisplayName = mAuth.currentUser!!.displayName!!
                user.Avatar = mAuth.currentUser!!.photoUrl.toString()
                Log.d("KeepMeLogin", user.DisplayName)
                Log.d("KeepMeLogin", user.Avatar)
                it.onSuccess(user)
            } else {
                it.onError(Exception("404"))
            }
        }
        return result
    }
}