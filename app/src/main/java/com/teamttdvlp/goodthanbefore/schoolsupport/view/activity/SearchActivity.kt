package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LoginViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.SearchDirectoryViewPagerAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.w3c.dom.Text

class SearchActivity : AppCompatActivity() {

    lateinit var adapter : SearchDirectoryViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        addControls()
        addEvents()
        Log.e("Key",intent.extras.getString("Keyword") + "Hahaha")
    }

    fun addControls () {
        var view1 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        var view2 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        var view3 = LayoutInflater.from(this).inflate(R.layout.item_tablayout_searchview, activity_search, false) as TextView
        view1.text = "Stories"
        view1.typeface = Typeface.DEFAULT_BOLD
        view2.text = "People"
        view3.text = "Tags"
        adapter = SearchDirectoryViewPagerAdapter(supportFragmentManager)
        search_vpg_search.adapter = adapter
        search_tablayout_directory.setupWithViewPager(search_vpg_search)
        search_tablayout_directory.getTabAt(0)?.customView = view1
        search_tablayout_directory.getTabAt(1)?.customView = view2
        search_tablayout_directory.getTabAt(2)?.customView = view3

        search_vpg_search.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            var prevPageSelectedPos = 0
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position != prevPageSelectedPos) {
                    (search_tablayout_directory.getTabAt(position)?.customView as TextView).typeface = Typeface.DEFAULT_BOLD
                    (search_tablayout_directory.getTabAt(prevPageSelectedPos)?.customView as TextView).typeface = Typeface.DEFAULT
                    prevPageSelectedPos = position
                }
            }

        })
    }

    fun addEvents () {

    }
}
