package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityInterestBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.InterestRecylerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.InterestViewModel

class InterestActivity : AppCompatActivity() {

    lateinit var rcv_interest_adapter : InterestRecylerViewAdapter

    lateinit var mBinding : ActivityInterestBinding

    lateinit var mViewModel : InterestViewModel

    var interest_list = ArrayList<Interest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_interest)
        addControl()
        addEvents()
        mViewModel.loadData {
            interest_list.add(it)
            rcv_interest_adapter.notifyDataSetChanged()
        }
    }

    fun addControl () {
        mViewModel = getViewModel()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_interest)
        rcv_interest_adapter = InterestRecylerViewAdapter(this, interest_list)
        rcv_interest_adapter.adaptFor(mBinding.lvInterest)
    }

    fun addEvents () {

    }
}
