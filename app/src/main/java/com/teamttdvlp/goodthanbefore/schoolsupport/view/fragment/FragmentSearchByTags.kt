package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.SearchResultActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.SearchByTagRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_fragment_search_by_tags.*

class FragmentSearchByTags : androidx.fragment.app.Fragment() {

    lateinit var adapter : SearchByTagRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_search_by_tags, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
    }

    private fun addControls() {
        var list = ArrayList<String>()
        list.add("Toán hình học 12")
        list.add("Toán đại số 12")
        list.add("Hóa bài tập chương IV - Hóa 12")
        list.add("Bài tập sinh học 11")
        list.add("Bài tập tin học C/C++")
        list.add("Đại cương giáo dục quốc phòng")
        list.add("Toán cao cấp")
        list.add("Văn mẫu")
        adapter = SearchByTagRecyclerViewAdapter(context!!, list, search_by_tag_rcv_tags)
        search_by_tag_rcv_tags.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        search_by_tag_rcv_tags.adapter = adapter
        adapter.setOnItemClickListener (object : SearchByTagRecyclerViewAdapter.OnItemClickListener {
            override fun onItemClickListener(view: View, pos: Int) {
                Log.e("Pos", pos.toString())
                startActivity(Intent(context, SearchResultActivity::class.java))
            }
        })
    }
}