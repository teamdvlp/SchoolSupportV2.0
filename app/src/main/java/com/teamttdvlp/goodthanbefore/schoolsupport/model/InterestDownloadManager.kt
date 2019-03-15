package com.teamttdvlp.goodthanbefore.schoolsupport.model

import android.app.Application
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.IInterestDownload
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ONE_MEGABYTE
import java.lang.Exception

class InterestDownload (var app : Application) : IInterestDownload {

    val firestoreDB = FirebaseFirestore.getInstance()

    val storageDB = FirebaseStorage.getInstance()

    override fun loadInterest(onLoadSuccess : (interst : ArrayList<Interest>) -> Unit
                     ,onGetCollectionFailed: (Exception) -> Unit ) {

        firestoreDB.collection("Topic").get().
            addOnSuccessListener {
                val result : ArrayList<Interest> = ArrayList()
                for (doc in it.documents) {
                    val interest:Interest = Interest(doc["Avatar"].toString(), doc.id, doc["Description"].toString(), false)
                    result.add(interest)
                }
                onLoadSuccess(result)
            }.
            addOnFailureListener {
                it.printStackTrace()
                onGetCollectionFailed(it)
            }
    }

}