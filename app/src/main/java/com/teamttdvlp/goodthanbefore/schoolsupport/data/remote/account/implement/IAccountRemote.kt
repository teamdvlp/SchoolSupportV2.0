package com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.account.implement

import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single

interface IAccountRemote {
    fun loginAsGuest ()
    fun loginWithEmailAndPassword (email:String, password:String): Single<User>
    fun loginWithCredential (credential: AuthCredential) : Single<User>
    fun loginWithFacebook (token : AccessToken) : Single<User>
    fun loginWithGoogle (account: GoogleSignInAccount): Single<User>
    fun signUpWithEmailAndPassword (email: String, password: String,displayName:String) : Single<User>

}