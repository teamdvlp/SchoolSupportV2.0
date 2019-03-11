package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentGlobalBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Story
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.UserActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_global.*

class FragmentGlobal : Fragment() {

    lateinit var mBinding : FragmentGlobalBinding

    lateinit var adapter : PostRecyclerViewAdapter

    var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global, container, false)
        return mBinding.root
    }

    var mockList = ArrayList<Story?>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControl()
        addEvents()
    }

    fun addControl () {
        val mockAvatar = context!!.getDrawable(R.drawable.edt_account)
        val story = Story("Title", "Author", "30/4/1945", mockAvatar!!)
        mockList.add(story)
        mockList.add(story)
        mockList.add(story)

        adapter = PostRecyclerViewAdapter(context!!, mockList)
        adapter.adaptFor(rcv_tester)
    }

    fun addEvents () {
        global_btn_user.setOnClickListener {
            startActivity(Intent(context, UserActivity::class.java))
        }

        adapter.addOnScrollListener(object : PostRecyclerViewAdapter.OnScrollListener {
            override fun onScrollToFirstElement() {
                mBinding.globalDividerLine.apply {
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
        Handler().postDelayed ({

            val mockAvatar = context!!.getDrawable(R.drawable.edt_account)
            val story = Story("Title", "Author", "30/4/1945", mockAvatar!!)
            story.title += "3"
            for ( i in 0..2) {
                mockList.add(story)
            }

            // Bat buoc goi sau khi load du lieu, thoi la onScrollToEnd se khong chay nua
            adapter.endLoadingState()
            adapter.notifyDataSetChanged()

        }, 2000)
    }

}