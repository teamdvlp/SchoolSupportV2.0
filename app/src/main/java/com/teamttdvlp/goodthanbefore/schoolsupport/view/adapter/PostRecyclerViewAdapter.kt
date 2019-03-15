package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import java.text.SimpleDateFormat

const val LOADED_ITEM_VIEW = 0
const val WAITING_LOAD_VIEW = 1

class PostRecyclerViewAdapter(var context : Context, var item_list : ArrayList<Stories>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isLoading = false

    private var isEndOfList = false

    private var layoutManager : LinearLayoutManager? = null

    var mRecyclerView : RecyclerView? = null

    fun startLoadingState () {
        isLoading = true
    }

    fun endLoadingState () {
        isLoading = false
    }

    fun stillHasUnloadedData (z : Boolean) {
        isEndOfList = !z
    }

    fun adaptFor (recyclerView: RecyclerView) {
        mRecyclerView = recyclerView
        mRecyclerView!!.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        mRecyclerView!!.adapter = this
        layoutManager = recyclerView.layoutManager as LinearLayoutManager
    }

    /**
     * isLoading variable is used to
     * stop this listener (onScrollToEnd) from being invoke many times
     * while some outer modules is loading data to item_list
     * because onScrolledListener will be called each time user scroll our recycler view
     */
    fun addOnScrollListener (onScrollListener : OnScrollListener) {
        mRecyclerView!!.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!isEndOfList && !isLoading) {
                    if (layoutManager?.findLastVisibleItemPosition() == item_list.size) {
                        onScrollListener.onScrollToLastElement()
                    }
                }

                if (layoutManager?.findFirstCompletelyVisibleItemPosition() == 0) {
                    onScrollListener.onScrollToFirstElement()
                } else {
                    onScrollListener.onScroll()
                }
            }
        })
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == LOADED_ITEM_VIEW) {
            Log.e("On Create View Holder", "Loaded item")
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_lv_post, parent, false)
            LoadedViewDataHolder(itemView)
        } else {
            Log.e("On Create View Holder", "Waiting item")
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_lv_waiting_loading, parent, false)
            WaitingViewDataHolder(itemView)
        }
    }


    override fun getItemCount() : Int {
        return if (!isEndOfList)
        // Add one more element to show loading view
            item_list.size + 1
        else
            item_list.size
    }


    override fun getItemViewType (position: Int) : Int {
        return if (!isEndOfList) {
            if (position < item_list.size)
                LOADED_ITEM_VIEW
            else
                WAITING_LOAD_VIEW
        } else
            LOADED_ITEM_VIEW
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is LoadedViewDataHolder) {
            if (position < item_list.size) {
                val story = item_list[position]
                story?.let {
                    holder.apply {
//                        imgAvatar?.background = story.Avatar
                        txtPostTitle?.text = story.Title
                        txtAuthor?.text = story.Author
                        txtDate?.text = SimpleDateFormat("dd/MM/YYYY").format(story.PostedTime)
                    }
                }
            }
        }
    }

    class LoadedViewDataHolder (item_view : View) : RecyclerView.ViewHolder(item_view) {
        var imgAvatar : ImageView? = item_view.findViewById(R.id.item_ilp_img_avatar)
        var txtPostTitle : TextView? = item_view.findViewById(R.id.item_ilp_txt_post_title)
        var txtAuthor : TextView? = item_view.findViewById(R.id.item_ilp_txt_author)
        var txtDate : TextView? = item_view.findViewById(R.id.item_ilp_txt_date)
    }

    class WaitingViewDataHolder (item_view : View) : RecyclerView.ViewHolder(item_view)

    interface OnScrollListener {
        fun onScrollToFirstElement ()

        fun onScrollToLastElement ()

        fun onScroll ()
    }
}

