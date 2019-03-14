package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Story
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.PostRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_history.*

class FragmentHistory : androidx.fragment.app.Fragment() {
    lateinit var adapter : PostRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addControls() {
        var mockList = ArrayList<Story>()
        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.edt_account)!!))
        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.edt_account)!!))
        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.edt_account)!!))
        mockList.add(Story("Title", "Author", "Date", context!!.getDrawable(R.drawable.edt_account)!!))
        adapter = PostRecyclerViewAdapter(context!!, mockList)
        history_rcv_history.adapter = adapter
        history_rcv_history.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
    }

    private fun addEvents() {

    }

}
