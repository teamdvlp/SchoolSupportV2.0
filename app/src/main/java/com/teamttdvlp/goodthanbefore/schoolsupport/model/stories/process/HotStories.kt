//package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process
//
//import android.annotation.TargetApi
//import android.os.Build
//import android.util.Log
//import com.google.firebase.firestore.FirebaseFirestore
//import com.google.firebase.firestore.Query
//import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.stories.IHotStories
//import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
//import com.teamttdvlp.goodthanbefore.schoolsupport.support.Limited
//import com.teamttdvlp.goodthanbefore.schoolsupport.support.SP
//import com.teamttdvlp.goodthanbefore.schoolsupport.support.add_removeIdenticalItem
//import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.Result
//import kotlinx.coroutines.*
//import java.lang.Exception
//import java.time.LocalDateTime
//import java.time.format.DateTimeFormatter
//import kotlin.coroutines.resume
//interface GetStoryByTopic {
//    fun onGetStoryByTopicSuccess (result: ArrayList<Stories>)
//    fun onGetStoryByTopicFailed (e:Array<Exception?>)
//}
//class HotStories : IHotStories {
//    private var mHotCheckPoint : Long = 0
//    private var mFirebaseFirestore : FirebaseFirestore
//    var getStoryByTopicListener : GetStoryByTopic? = null
//
//    constructor() {
//        mFirebaseFirestore = FirebaseFirestore.getInstance()
//    }
//    override fun getHotStoriesByTopic(topic: String, to: Long) {
//        GlobalScope.launch {
//            var resultThreeHot = async {
//                supportGetHotStories(topic,
//                SP.Stories.CYCLE1VALUE, SP.Stories.CYCLE1LIFETIME,
//                Limited.unLimit)
//            }
//
//            var resultFiveHot = async {
//                supportGetHotStories(topic,
//                SP.Stories.CYCLE2VALUE, SP.Stories.CYCLE2LIFETIME,
//                Limited.unLimit)
//            }
//
//            var resultSeventHot = async {
//                supportGetHotStories(topic,
//                SP.Stories.CYCLE3VALUE, SP.Stories.CYCLE3LIFETIME,
//                Limited.unLimit)
//            }
//
//            val value : ArrayList<Stories> = ArrayList()
//            val result1  =  resultThreeHot.await()
//            val result2  =  resultFiveHot.await()
//            val result3 = resultSeventHot.await()
//
//            value.add_removeIdenticalItem(result1.result!!).add_removeIdenticalItem(result2.result!!).add_removeIdenticalItem(result3.result!!)
//
//            if (value.size == 0) {
//                val e : Array<Exception?> = arrayOf(result1.exception, result2.exception, result3.exception)
//                getStoryByTopicListener?.onGetStoryByTopicFailed(e)
//            } else {
//                getStoryByTopicListener?.onGetStoryByTopicSuccess(value)
//        }
//        }
//    }
//
//
//    @TargetApi(Build.VERSION_CODES.O)
//    private fun getCurrentDate () : String {
//        val dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy")
//        val now = LocalDateTime.now()
//        val result = dtf.format(now)
//        Log.d("CurrentDate","Current Date: ${result}")
//        return "02/03/2019"
//    }
//
//    private suspend fun supportGetHotStories (topic: String, cycleName: String, lifeCycle : String,  to: Long) : Result<ArrayList<Stories>> {
//        return suspendCancellableCoroutine { coroutine ->
//            var currentDate = getCurrentDate()
//            var fs = mFirebaseFirestore.collection(SP.Stories.COL_NAME)
//                .orderBy(cycleName, Query.Direction.DESCENDING)
//                .limit(2)
//                .whereArrayContains(lifeCycle, currentDate)
//                .whereEqualTo(SP.Stories.TOPIC, topic)
//                .whereGreaterThanOrEqualTo(cycleName, this.mHotCheckPoint)
//            if (to != Limited.unLimit) {
//                fs.whereLessThanOrEqualTo(cycleName, to)
//            }
//            fs.get()
//                .addOnCompleteListener({
//                    var result : Result<ArrayList<Stories>> = Result()
//                    if (it.isSuccessful) {
//                        val arr : ArrayList<Stories> =  ArrayList()
//                        for (doc in it.result!!.documents) {
//                            arr.add(doc.toObject(Stories::class.java)!!)
//                        }
//                        result.success(arr)
//                    } else {
//                        result.failed(it.exception!!)
//                    }
//                    coroutine.resume(result)
//                })
//        }
//
//    }
//
//    override  fun getHotStoriesByTopic(topics: ArrayList<String>, to: Long) {
//
//    }
//
//    private suspend fun supportGetMultipleHotStories (topic:String, to:Long) : Result<ArrayList<Stories>> {
//        return suspendCancellableCoroutine {
//            getStoryByTopicListener = object : GetStoryByTopic {
//                override fun onGetStoryByTopicSuccess(result: ArrayList<Stories>) {
//
//                }
//
//                override fun onGetStoryByTopicFailed(e: Array<Exception?>) {
//                }
//
//            }
//        }
//    }
//
//    override fun setHotCheckPoint(mark: Long) {
//        mHotCheckPoint = mark
//    }
//
//}