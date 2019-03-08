package com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.customview.DrawableCheckBox
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.Interest

class InterestRecylerViewAdapter (var context: Context, var item_list : ArrayList<Interest> = ArrayList()): RecyclerView.Adapter<InterestRecylerViewAdapter.DataViewHolder>() {

    var recyclerView : RecyclerView? = null

    var selected_itemList = ArrayList<Interest>()

    var onCheckboxCheckedChangeListener : (CompoundButton, Boolean) -> Unit = {
        checkBox, isChecked ->

        var view_child = checkBox.parent.parent
        var position = recyclerView?.indexOfChild(view_child as ViewGroup)

        position?.let {
            var clicked_item = item_list[position]
            if (isChecked && !selected_itemList.contains(clicked_item)) {
                selected_itemList.add(clicked_item)
            } else {
                selected_itemList.remove(clicked_item)
            }
        }

        Log.e("Size", selected_itemList.size.toString())
    }

    fun adaptFor (recyclerView : RecyclerView) {
        this.recyclerView = recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = this
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        var layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var item_view = layoutInflater.inflate(R.layout.item_lv_select_interest, parent, false)
        return DataViewHolder(item_view)
    }

    override fun getItemCount(): Int {
        return item_list.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        var item = item_list[position]
        holder.img_avatar.setImageDrawable(item.avatar)
        holder.txt_name.text = item.name
        holder.txt_description.text = item.description
        holder.rbtn_is_selected.setOnCheckedChangeListener(onCheckboxCheckedChangeListener)
    }


    inner class DataViewHolder(itemView : View) : RecyclerView.ViewHolder (itemView) {
        var img_avatar : ImageView = itemView.findViewById(R.id.item_lsi_img_avatar)
        var txt_name : TextView = itemView.findViewById(R.id.item_lsi_txt_interest_name)
        var txt_description  : TextView = itemView.findViewById(R.id.item_lsi_txt_description)
        var rbtn_is_selected : DrawableCheckBox = itemView.findViewById(R.id.item_lsi_rbtn_is_selected)
    }
}