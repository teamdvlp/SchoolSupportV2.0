package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentFragmentSearchByStoriesBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentWriteStoryBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.view.RecyclerViewLoadmoreAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.ReadStoriesActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.fragment_fragment_search_by_stories.*

class FragmentSearchByStories : androidx.fragment.app.Fragment() {

    lateinit var mBinding : FragmentFragmentSearchByStoriesBinding

    lateinit var activityViewModel : SearchViewModel

    lateinit var rcvSearchAdapter : PostRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_fragment_search_by_stories, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
    }

    private fun addControls() {
        activityViewModel = activity!!.getViewModel()
        activityViewModel.resultStories.observe(this, Observer {
            rcvSearchAdapter = PostRecyclerViewAdapter(it, context!!)
            rcv_search.layoutManager = LinearLayoutManager(context!!, RecyclerView.VERTICAL, false)
            rcv_search.adapter = rcvSearchAdapter
            rcvSearchAdapter.stillHasUnloadedData(false)
            logError("Size : ${it.size}")
            addEvents()
        })
    }

    private fun addEvents() {
        rcvSearchAdapter.addOnItemClickedListener(object : RecyclerViewLoadmoreAdapter.OnItemClickListener {
            override fun onClicked(position: Int) {
                var i : Intent = Intent(activity, ReadStoriesActivity::class.java)
                i.putExtra("Story",activityViewModel.resultStories.value!![position])
                startActivity(i)
            }

        })
    }


}
