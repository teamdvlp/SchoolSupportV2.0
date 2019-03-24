package com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UpdateInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.WriteInfoEvent

class UserInfo : IUserInfo {

    private var mFirestoreRef : FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun writeInfo(user: User, callback: WriteInfoEvent) {
        mFirestoreRef.collection("Users").document(user.Id).set(user)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback.onWriteInfoSuccess()
                } else {
                    callback.onWriteInfoFailed(it.exception)
                }
            }

    }

    override fun readInfo(userId:String, callback: ReadInfoEvent) {
        Log.d("radInfo", userId)
        mFirestoreRef.collection("Users").document(userId).get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result : User? = it.getResult()!!.toObject(User::class.java)
                    callback.onReadInfoSuccess(result)
                } else {
                    callback.onReadInfoFailed(it.exception)
                }
            }
    }

    override fun updateInfo(user: User, callback: UpdateInfoEvent) {
        mFirestoreRef.collection("Users")
            .document(user.Id)
            .set(user)
    }
}