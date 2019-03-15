package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentGlobalBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.UserActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_global.*

class FragmentGlobal : Fragment() {

    private lateinit var mBinding : FragmentGlobalBinding

    private lateinit var adapter : PostRecyclerViewAdapter
    private lateinit var activityModel : MainViewModel
    private var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global, container, false)
        return mBinding.root
    }

    var mockList = ArrayList<Stories>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControl()
        addEvents()
    }

    fun addControl () {
        activityModel = activity!!.getViewModel()
        val mockAvatar = context!!.getDrawable(R.drawable.edt_account)
        val story = Stories()
        mockList.add(story)
        mockList.add(story)
        mockList.add(story)
        mockList.add(story)
        mockList.add(story)
        mockList.add(story)

        adapter = PostRecyclerViewAdapter(mockList,context!!)
        rcv_tester!!.adapter = adapter
        adapter.adaptFor(rcv_tester)
    }

    fun addEvents () {
        global_btn_user.setOnClickListener {
            Log.d("kiemtra", activityModel.currentUser.DisplayName)
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("User", activityModel.currentUser)
            startActivity(intent)
        }

        adapter.addOnScrollListener(object : RecyclerViewLoadmoreAdapter.OnScrollListener {
            override fun onScrollToFirstElement() {
                mBinding.globalDividerLine.apply {
                    Log.d("loadne", "loading")
                    if (visibility == VISIBLE)
                        visibility = INVISIBLE
                }
            }

            override fun onScrollToLastElement() {
                // Bat buoc goi truoc khi load du lieu, thoi la bi bug
                adapter.startLoadingState()
                loadMore()
            }

            override fun onScroll() {
                mBinding.globalDividerLine.apply {
                    if (visibility == INVISIBLE)
                        visibility = VISIBLE
                }
            }

        })
    }

    fun loadMore () {
}}
