package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.SpawnStories
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_bookmark.*


class FragmentBookmarks : Fragment() {

    lateinit var rcv_adapter : PostRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmark, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addEvents() {

    }

    private fun addControls() {
        var mockList = ArrayList<Stories>()
        mockList.addAll(SpawnStories().spawnStories(10))
        rcv_adapter = PostRecyclerViewAdapter(context!!, mockList)
        bookmark_rcv_post.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rcv_adapter.adaptFor(bookmark_rcv_post)
    }

}
