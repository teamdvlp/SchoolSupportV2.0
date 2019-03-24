package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import android.graphics.Bitmap
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IPostNewStory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.UploadAvatarEvent
import java.io.ByteArrayOutputStream
import java.lang.Exception

class PostNewStory : IPostNewStory {
    private var mFirebaseStorage: FirebaseStorage
    private var mFirestore : FirebaseFirestore

    constructor() {
        mFirebaseStorage = FirebaseStorage.getInstance()
        mFirestore = FirebaseFirestore.getInstance()
    }

    override fun postStory(story: Stories, storyAvatar:Bitmap, listener : PostNewStoryEvent) {
        val storyId : String = mFirestore.collection("Stories").document().id
        story.Id = storyId
        Log.d("StoryId", story.Id)
        postStoryAvatar(storyAvatar, story.Id,object : UploadAvatarEvent {
            override fun onUploadSuccess(downloadUri: String) {
                story.Avatar = downloadUri
                mFirestore.collection("Stories").document(storyId).set(story)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            listener.onPostNewStorySuccess()
                        } else {
                            listener.onPostNewStoryFailed(it.exception)
                        }
                    }
            }

            override fun onUploadFailed(message: String) {
                listener.onPostNewStoryFailed(Exception(message))
            }

        })

    }

    override fun postStoryAvatar(bitmap: Bitmap, storyId:String, listener : UploadAvatarEvent) {
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val data = baos.toByteArray()
        mFirebaseStorage.getReference("Stories/${storyId}/avatar.png")
            .putBytes(data)
            .addOnSuccessListener {
                if (it.error == null) {
                    mFirebaseStorage.getReference("Stories/${storyId}/avatar.png")
                        .downloadUrl
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                listener.onUploadSuccess(downloadUri = it.result.toString())
                            } else{
                                listener.onUploadFailed(it.exception!!.message!!)
                            }
                        }
                } else {
                    Log.d("PostUploadFailed", it.error?.message)
                    listener.onUploadFailed(it.error?.message!!)
                }
            }
        }
}