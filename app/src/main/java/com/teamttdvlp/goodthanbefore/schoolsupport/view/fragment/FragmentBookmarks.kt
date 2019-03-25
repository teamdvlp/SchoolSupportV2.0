package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentBookmarkBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.SpawnStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.BookmarkViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.MainViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ViewProfileViewModel
import kotlinx.android.synthetic.main.fragment_bookmark.*


class FragmentBookmarks : Fragment(), GetMultipleStories, RecyclerViewLoadmoreAdapter.OnScrollListener {

    private lateinit var mAdapter : PostRecyclerViewAdapter
    private lateinit var mViewModel : BookmarkViewModel
    private lateinit var mBinding : FragmentBookmarkBinding
    private lateinit var activityViewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_bookmark, container, false)
        activityViewModel = activity!!.getViewModel()
        mViewModel = getViewModel()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
        setup()
    }

    private fun setup() {
    }

    private fun addEvents() {

    }

    override fun onGetMultipleStoriesSuccess(result: ArrayList<Stories>) {
        mViewModel.storyData.addAll(result)
        mAdapter.endLoadingState()
        mAdapter.notifyDataSetChanged()
    }

    override fun onGetMultipleStoriesFailed() {

    }

    override fun onScrollToFirstElement() {

    }

    override fun onScrollToLastElement() {
        mAdapter.startLoadingState()
        mViewModel.getUserBookmarkStories(3, this)
    }

    override fun onScroll() {

    }

    private fun addControls() {
        mAdapter = PostRecyclerViewAdapter(mViewModel.storyData, context!!)
        mAdapter.stillHasUnloadedData(false)
        mBinding.bookmarkRcvPost.adapter = mAdapter
        bookmark_rcv_post.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mAdapter.adaptFor(mBinding.bookmarkRcvPost)
        mAdapter.addOnScrollListener(this)
    }

}
