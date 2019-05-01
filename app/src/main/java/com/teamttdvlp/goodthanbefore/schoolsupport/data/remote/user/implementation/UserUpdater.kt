package com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.user.implementation

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.teamttdvlp.goodthanbefore.schoolsupport.data.remote.user.implement.IUserUpdater
import io.reactivex.Completable

class UserUpdater : IUserUpdater {
    private var mFirestore : FirebaseFirestore
    constructor() {
        mFirestore = FirebaseFirestore.getInstance()
    }

    override fun updateUserProfile(value: Map<String, Any>, userId: String) : Completable {
        var result = Completable.create { emitter ->
            mFirestore.collection("Users")
                .document(userId)
                .set(value, SetOptions.merge())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        emitter.onComplete()
                    } else {
                        emitter.onError(it.exception!!)
                    }
                }
        }
        return result
    }
}
