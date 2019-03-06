package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import android.content.res.Resources
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity

const val ONE_MEGABYTE : Long = 1024 * 1024


/**
 * @see InterestActivity
 */
class InterestViewModel (var app : Application): AndroidViewModel (app) {
    val firestoreDB = FirebaseFirestore.getInstance()

    val storageDB = FirebaseStorage.getInstance()

    var interest_list_ld = MutableLiveData<ArrayList<Interest>>()

    var ld = MutableLiveData<Drawable>()

    var dataReceiver = ArrayList<Interest>()

    fun loadData (onAnImageLoadSuccess : (interst : Interest) -> Unit) {

        firestoreDB.collection("Topic").get().
            addOnSuccessListener {
                loadImage(it.documents, onAnImageLoadSuccess)
            }.
            addOnFailureListener {
                it.printStackTrace()
                Log.e("Error", "InterestViewModel.kt, loadData()")
            }

    }

    fun loadImage (docs : List<DocumentSnapshot>, onAnImageLoadSuccess : (interst : Interest) -> Unit) {
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

//    fun loadImage (pos : Int, docs : List<DocumentSnapshot>) {
//
//        if (pos < docs.size) {
//            var doc = docs[pos]
//            storageDB.getReference(doc["Avatar"].toString()).getBytes(ONE_MEGABYTE)
//                .addOnSuccessListener {byteArray ->
//                    try {
//
//                        var drawable : Drawable = BitmapDrawable(app.resources, BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size))
//                        dataReceiver.add(Interest(drawable, doc.id, doc["Description"].toString()))
//
//                    } catch (ex : Exception) {
//                        ex.printStackTrace()
//                        Log.e("Error", "InterestViewModel.kt, loadImage(), line 59")
//                    } finally {
//                        loadImage(pos + 1, docs)
//                    }
//                }.addOnCanceledListener {
//                    Log.e("Error", "InterestViewModel.kt, loadImage()")
//                }
//        } else {
//            interest_list_ld.postValue(dataReceiver)
//        }
//
//
//    }

}