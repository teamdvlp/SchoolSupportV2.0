package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import kotlinx.android.synthetic.main.activity_search_bar.*

class SearchBarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_bar)
        search_bar_edt_search.setOnEditorActionListener {
            v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                var keyword = search_bar_edt_search.text.toString()
                var intent = Intent(this, SearchActivity::class.java).putExtra("Keyword", keyword)
                startActivity(intent)
            }
            return@setOnEditorActionListener true
        }
    }
}
