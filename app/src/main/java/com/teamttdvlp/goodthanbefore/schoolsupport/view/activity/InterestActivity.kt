package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.InterestRecylerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.InterestViewModel
import kotlinx.android.synthetic.main.activity_interest.*
import java.lang.Exception

private const val REQUIRE_SELECTED_CONTENT_COUNT = 3
class InterestActivity : AppCompatActivity() {

    lateinit var rcv_interest_adapter : InterestRecylerViewAdapter

    lateinit var mViewModel : InterestViewModel

    var interestList = ArrayList<Interest>()

    var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        user = intent.getParcelableExtra("User")
        addControl()
        addEvents()
        loadData()
    }

    fun addControl () {
        mViewModel = getViewModel()
        rcv_interest_adapter = InterestRecylerViewAdapter(this, interestList)
        rcv_interest_adapter.adaptFor(lvInterest)
    }

    fun addEvents () {
        btnNext.setOnClickListener {
            if (rcv_interest_adapter.selected_itemList.size >= REQUIRE_SELECTED_CONTENT_COUNT)
            {
                try {
                    for (interest in rcv_interest_adapter.selected_itemList) {
                        user!!.Interests.add(interest.name)
                    }
                    mViewModel.writeUserToFS(user!!)
                } catch (ex :Exception) {
                    ex.printStackTrace()
                }
            } else {
                Log.e("Ok", rcv_interest_adapter.selected_itemList.size.toString())
            }
        }
    }

    fun loadData () {

        val onAnImageLoadSuccess : (Interest) -> Unit = {
            interestList.add(it)
            rcv_interest_adapter.notifyDataSetChanged()
        }

        val onLoadInfoFailed : (Exception) -> Unit = {
            // Process error
        }

        mViewModel.loadData (onAnImageLoadSuccess, onLoadInfoFailed)
    }
}
