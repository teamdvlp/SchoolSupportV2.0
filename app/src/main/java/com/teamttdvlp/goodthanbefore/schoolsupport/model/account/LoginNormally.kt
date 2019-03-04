package com.teamttdvlp.goodthanbefore.schoolsupport.model.account

import com.google.firebase.auth.FirebaseAuth
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
                user.id = it.result!!.additionalUserInfo.providerId
                callback.onSuccess(user)
            }
        })
    }
}

