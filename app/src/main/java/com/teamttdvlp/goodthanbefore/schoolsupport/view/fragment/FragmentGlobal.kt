package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
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
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoriesActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SearchBarActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.UserActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.GlobalViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_global.*

class FragmentGlobal : Fragment(), RecyclerViewLoadmoreAdapter.OnItemClickListener {

    private lateinit var mBinding : FragmentGlobalBinding

    private lateinit var mViewMode: GlobalViewModel
    private lateinit var adapter : PostRecyclerViewAdapter
    private lateinit var activityModel : MainViewModel
    private var isLoading = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControl()
        addEvents()
    }

    fun addControl () {
        activityModel = activity!!.getViewModel()
        mViewMode = getViewModel()

        adapter = PostRecyclerViewAdapter(mViewMode.storyData,context!!)
        rcv_tester!!.adapter = adapter
        adapter.adaptFor(rcv_tester)
    }

    fun addEvents () {
        global_btn_user.setOnClickListener {
            val intent = Intent(context, UserActivity::class.java)
            startActivity(intent)
        }

        global_btn_search.setOnClickListener {
            startActivity(Intent(context!!, SearchBarActivity::class.java))
        }

        adapter.addOnItemClickedListener(this)

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

    override fun onClicked(position: Int) {
        val storyClicked:Stories = mViewMode.storyData[position]
        val intent = Intent(activity,ReadStoriesActivity::class.java)
        intent.putExtra("Story", storyClicked)
        startActivity(intent)
    }

    fun loadMore () {
        var checkPointThree : Long = 0
        var checkPointFive : Long= 0
        var checkPointSeven :Long= 0
        if (mViewMode.storyData.size > 0) {
            checkPointThree = mViewMode.storyData[mViewMode.storyData.size-1].ThreeHotDayCycle
            checkPointFive = mViewMode.storyData[mViewMode.storyData.size-1].FiveHotDayCycle
            checkPointSeven = mViewMode.storyData[mViewMode.storyData.size-1].SevenHotDayCycle
        } else {
            checkPointThree = Long.MAX_VALUE
            checkPointFive = Long.MAX_VALUE
            checkPointSeven = Long.MAX_VALUE
        }

        Log.d("checkpoints5: " , checkPointFive.toString())
        Log.d("checkpoints7: " , checkPointSeven.toString())
        Log.d("checkpoints3: " , checkPointThree.toString())

        mViewMode.loadHotStories(CurrentUser.currentUser!!.Interests, checkPointThree,checkPointFive,checkPointSeven, object:GetMultipleStories {
            override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
                mViewMode.storyData.addAll(result)
                mViewMode.removeDuplicateItem()
                adapter.notifyDataSetChanged()
                adapter.endLoadingState()
            }

            override fun onGetMultipleStoriesFailed() {
                adapter.isEndOfList = true
            }

        })
    }
}
