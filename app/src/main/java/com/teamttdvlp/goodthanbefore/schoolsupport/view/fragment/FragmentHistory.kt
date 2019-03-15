package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentHistoryBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.HistoryViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ViewProfileViewModel

class FragmentHistory : Fragment(), GetMultipleStories, RecyclerViewLoadmoreAdapter.OnScrollListener {

    private lateinit var mBinding:FragmentHistoryBinding
    private lateinit var activityViewModel:ViewProfileViewModel
    private lateinit var mViewModel:HistoryViewModel

    private lateinit var mAdapter:PostRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_history, container, false)
        activityViewModel  = activity!!.getViewModel()
        mViewModel = getViewModel({return@getViewModel HistoryViewModel(activityViewModel.currentUser)})
        addControls()
        addEvents()
        setup()
        return mBinding.root
    }

    private fun setup() {
    }

    private fun addEvents() {

    }


    override fun onScrollToFirstElement() {
        mBinding.line.apply {
            Log.d("loadne", "loading")
            if (visibility == View.VISIBLE)
                visibility = View.INVISIBLE
        }
    }

    override fun onScrollToLastElement() {
        mAdapter.startLoadingState()
        mViewModel.getHistoryStories(this)
    }

    override fun onScroll() {
        mBinding.line.apply {
            if (visibility == View.INVISIBLE)
                visibility = View.VISIBLE
        }
    }


    private fun addControls() {
        mAdapter = PostRecyclerViewAdapter(mViewModel.storyData, context!!)
        mBinding.lvHistorialStories.adapter = mAdapter
        mAdapter.adaptFor(mBinding.lvHistorialStories)
        mAdapter.addOnScrollListener(this)
    }

    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
        mViewModel.storyData.addAll(result)
        Log.d("loadHistory", mViewModel.storyData[0].Id)
        mAdapter.endLoadingState()
        mAdapter.notifyDataSetChanged()
    }

    override fun onGetMultipleStoriesFailed() {
        mAdapter.endLoadingState()
        mAdapter.notifyDataSetChanged()
    }

    companion object {
        private val mInstance : FragmentHistory = FragmentHistory()
        fun getInstance () : FragmentHistory {
            return mInstance
        }
    }
}
