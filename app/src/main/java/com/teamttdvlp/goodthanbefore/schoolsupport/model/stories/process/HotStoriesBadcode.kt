package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process

import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IHotStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetHotStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleHotStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.Result
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.resume


class HotStoriesBadcode : IHotStories {
    private var mFireStore : FirebaseFirestore
    constructor() {
        mFireStore = FirebaseFirestore.getInstance()
    }

    override fun getHotStoriesByTopic(topic: String, to: Long , listener: GetHotStoryEvent?) {
        mFireStore.collection("Stories")
            .whereEqualTo("topic", topic)
            .whereEqualTo("isHot", true)
            .limit(2)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val result : ArrayList<Stories> = ArrayList()
                    for (doc in it.result!!.documents) {
                        result.add(doc.toObject(Stories::class.java)!!)
                    }
                    listener?.onGetHotStorySuccess(result)
                } else {
                    listener?.onGetHotStoryFailed(it.exception)
                }
            }
    }

    override fun getHotStoriesByTopic(topics: ArrayList<String>, to: Long, listener:GetMultipleHotStoryEvent?) {
        GlobalScope.launch {
            val mainResult : ArrayList<Stories> = ArrayList()
            val mainError : ArrayList<Exception?> = ArrayList()
            val asystask : ArrayList<Deferred<Result<ArrayList<Stories>>>> = ArrayList()
            for (topic in topics) {
                asystask.add(async { getHotStorySuspend(topic, to)})
            }

            for (job in asystask) {
                    val r =  job.await()
                    mainResult.addAll(r.result!!)
                    if (r.isFailed) {
                        mainError.add(r.exception!!)
                    }
            }
            if (mainResult.size > 0) {
                listener?.onGetMultipleHotStoriesSuccess(mainResult)
            } else {
                listener?.onGetMultipleHotStoriesFailed(mainError)
            }
        }

    }

    private suspend fun getHotStorySuspend (topic:String , to :Long) : Result<ArrayList<Stories>> {
        val mResult : Result<ArrayList<Stories>> = Result()
        return suspendCancellableCoroutine {coroutine->
            getHotStoriesByTopic(topic, to, object : GetHotStoryEvent {
                override fun onGetHotStorySuccess(result: ArrayList<Stories>) {
                    mResult.success(result)
                    coroutine.resume(mResult)
                }

                override fun onGetHotStoryFailed(e: Exception?) {
                    mResult.failed(e!!)
                    coroutine.resume(mResult)
                }
            })
        }
    }

    override  fun setHotCheckPoint(mark: Long) {

    }

}