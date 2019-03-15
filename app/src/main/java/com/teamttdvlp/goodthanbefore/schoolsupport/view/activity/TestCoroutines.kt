package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.Result
import kotlinx.coroutines.*
import kotlin.coroutines.resume

class TestCoroutines : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_coroutines)
        test()
        var id : ArrayList<String> = ArrayList()
        id.add("1415363")
        id.add("16820237")
        id.add("16210734")
        id.add("1992020")
        id.add("44796052")
        id.add("40028270")
        id.add("4675821")
        id.add("45272806")
        id.add("49737436")
        getStories(id)
    }

    fun test () {
        GlobalScope.launch(Dispatchers.Default) {
            delay(10000)
            GlobalScope.launch (Dispatchers.Main)
            {
                findViewById<TextView>(R.id.tet).text = "hehehe"
            }
        }
    }

    fun getStories (id : ArrayList<String>) {
        GlobalScope.launch {
            val job : ArrayList<Deferred<Result<Stories>>> = ArrayList()
            var failedCount = 0
            for (i in id) {
                job.add(async { getABCXYZ(i) })
            }
            val result:ArrayList<Stories> = ArrayList()
            for (job in job) {
                var a = job.await().result
                if (a!=null) {
                    result.add(a)
                }
            }
            if (failedCount == id.size) {
                // Failed
            } else {
            GlobalScope.launch(Dispatchers.Main) {
                Success(result)
            }
            }
        }
    }

    fun Success(stories:ArrayList<Stories>) {
        Log.d("yeahhh", stories.size.toString())
    }

    suspend fun getABCXYZ (id:String) : Result<Stories>{
        return suspendCancellableCoroutine { coroutine ->
            FirebaseFirestore.getInstance().collection("Stories")
                .document(id)
                .get()
                .addOnCompleteListener({
                    val result : Result<Stories> = Result()
                    if (it.isSuccessful) {
                        val stories : Stories? = it.result?.toObject(Stories::class.java)
                        if (stories != null) {
                            result.success(stories)
                        }
                    } else {
                        result.failed(it.exception)
                    }
                    coroutine.resume(result)
                })
        }
    }
}
