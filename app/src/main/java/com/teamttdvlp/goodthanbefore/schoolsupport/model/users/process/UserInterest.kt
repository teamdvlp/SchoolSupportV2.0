package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserInterest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetUserInterestEvent

class UserInterest : IUserInterest{
    private var mFirebaseFirestore : FirebaseFirestore
    constructor() {
        mFirebaseFirestore = FirebaseFirestore.getInstance()
    }
    override fun getUserInterest(userId: String, callback:GetUserInterestEvent) {
        mFirebaseFirestore.collection("Users")
            .document(userId)
            .collection("StaticData")
            .document("Interests")
            .get()
            .addOnCompleteListener({
                if (it.isSuccessful) {
                    var data : List<String> = if(it.result?.get("Interests") != null)
                        it.result!!.get("Interests") as List<String> else ArrayList<String>()
                    var result = ArrayList<String>()
                    result.addAll(data)
                    callback.onGetUserInterestSuccess(result)
                } else {
                    callback.onGetUserInterestFailed(it.exception)
                }
            })
    }
}