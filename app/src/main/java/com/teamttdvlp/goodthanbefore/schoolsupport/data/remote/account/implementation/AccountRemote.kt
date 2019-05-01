package com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.account.implementation

import android.util.Log
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.account.implement.IAccountRemote
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import io.reactivex.Single
import java.lang.Exception

class AccountRemote : IAccountRemote {
    private var mAuth : FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }

    override fun loginAsGuest() {

    }

    override fun loginWithEmailAndPassword(email: String, password: String): Single<User> {
        var mResult = Single.create<User> { emitter ->
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
                if (it.isSuccessful) {
                    var user = User()
                    user.Id = it.result!!.user.uid
                    emitter.onSuccess(user)
                } else {
                    if (it.exception != null)
                        emitter.onError(it.exception!!)
                }
            })
        }
        return mResult
    }

    override fun loginWithCredential(credential: AuthCredential): Single<User> {
        var mResult = Single.create<User> { emitter ->
            mAuth.signInWithCredential(credential).addOnCompleteListener({
                if (it.isSuccessful) {
                    val result = User()
                    result.Id = it.result!!.user.uid
                    result.Avatar = it.result!!.user!!.photoUrl.toString()
                    result.DisplayName = it.result!!.user!!.displayName!!
                    emitter.onSuccess(result)
                } else {
                    if (it.exception!= null) {
                        emitter.onError(it.exception!!)
                    }
                }
            })
        }
        return mResult
    }

    override fun loginWithFacebook(token: AccessToken): Single<User> {
        val mCredential : AuthCredential = FacebookAuthProvider.getCredential(token.token)
        return loginWithCredential(mCredential)
    }

    override fun loginWithGoogle(account: GoogleSignInAccount): Single<User> {
        var mCredential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
        return loginWithCredential(mCredential)
    }

        override fun signUpWithEmailAndPassword(email: String, password: String, displayName: String): Single<User> {
            var result = Single.create<User> {emitter ->
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        var user = User()
                        user.DisplayName = displayName
                        user.Id = it.result!!.user.uid
                        emitter.onSuccess(user)
                    } else {
                        emitter.onError(it.exception!!)
                    }
                }
            }
            return result
    }

}