package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.google.firebase.auth.FirebaseAuth
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent

class SignUp {
    private lateinit var mAuth: FirebaseAuth
    constructor() {
        mAuth = FirebaseAuth.getInstance()
    }
    fun signup(email: String, password: String, callback: LoginEvent) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener({
            if (it.isSuccessful) {
                var user = User()
                user.id = it.result!!.additionalUserInfo.providerId
                callback.onSuccess(user)
            }
        })
    }
}

