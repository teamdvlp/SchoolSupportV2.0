package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account.ILoginNormally
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class LoginNormally : ILoginNormally {
    private var mAuth:FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    override fun login(email: String, password: String, callback:LoginEvent) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener({
            if (it.isSuccessful) {
                var user = User()
                user.Id = it.result!!.user.uid
                user.DisplayName = it.result!!.user.displayName!!
                user.Avatar = it.result!!.user.photoUrl.toString()
                callback.onLoginSuccess(user)
            } else {
                callback.onLoginFailed(it.exception)
            }
        })
    }
}

