package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.teamttdvlp.goodthanbefore.schoolsupport.model.InterestSetUpManager
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity
import java.lang.Exception

const val ONE_MEGABYTE : Long = 1024 * 1024


/**
 * @see InterestActivity
 */
class InterestViewModel (var app : Application): AndroidViewModel (app) {


    var interest_list_ld = MutableLiveData<ArrayList<Interest>>()

    var ld = MutableLiveData<Drawable>()

    var dataReceiver = ArrayList<Interest>()

    var mManager = InterestSetUpManager(app)

    fun loadData (onAnImageLoadSuccess : (interst : Interest) -> Unit, onLoadFailed: (Exception) -> Unit) {
        mManager.loadInterest(onAnImageLoadSuccess, onLoadFailed)
    }

    fun writeUserToFS(user: User) {
        mManager.writeUserToFS(user)
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