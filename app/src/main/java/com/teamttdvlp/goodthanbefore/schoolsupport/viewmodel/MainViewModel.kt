package com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import java.lang.Exception

class MainViewModel : ViewModel() {

    var mStorageRef = FirebaseStorage.getInstance()

    fun setUpCurrentUser () {
        mStorageRef.getReference("Users/${CurrentUser.currentUser!!.Id}/Avatar.png").getBytes(1024 * 1024)
            .addOnSuccessListener {
                var bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                CurrentUser.bitmapUserAvatar = bitmap
            }
            .addOnFailureListener {
                Log.e("FAILED", "FAILEDDDDDDDDDDD")
            }
    }
}