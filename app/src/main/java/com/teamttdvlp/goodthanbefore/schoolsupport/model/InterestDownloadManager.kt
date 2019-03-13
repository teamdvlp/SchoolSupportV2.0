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

    override fun loadInterest(onAnImageLoadSuccess : (interst : Interest) -> Unit
                     ,onGetCollectionFailed: (Exception) -> Unit ) {

        firestoreDB.collection("Topic").get().
            addOnSuccessListener {
                loadInterestInfo(it.documents, onAnImageLoadSuccess)
            }.
            addOnFailureListener {
                it.printStackTrace()
                onGetCollectionFailed(it)
            }
    }

    private fun loadInterestInfo(docs : List<DocumentSnapshot>, onAnImageLoadSuccess : (interst : Interest) -> Unit) {
        for (doc in docs) {
            storageDB.getReference(doc["Avatar"].toString()).getBytes(ONE_MEGABYTE)
                .addOnSuccessListener { byteArray ->

                    var drawable : Drawable = BitmapDrawable(app.resources, BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
                    onAnImageLoadSuccess(Interest(drawable, doc.id, doc["Description"].toString()))

                }.addOnCanceledListener {
                    Log.e("Error", "InterestViewModel.kt, loadImage()")
                }
        }
    }


}