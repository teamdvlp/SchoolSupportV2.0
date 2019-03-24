package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.WriteStoriesViewModel
import kotlinx.android.synthetic.main.activity_write_stories.*
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityWriteStoriesBinding

class WriteStoriesActivity : AppCompatActivity() {

    lateinit var mViewModel : WriteStoriesViewModel

    lateinit var mViewBinding : ActivityWriteStoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_stories)
        addControls()
        addEvents()
        editor.toolbar.setOnInsertImageButtonClickListener {
            Toast.makeText(this, "Sao anh van thay em buon", Toast.LENGTH_LONG).show()
        }
    }

    private fun addControls() {

    }

    private fun addEvents() {
        btn_next.setOnClickListener {
            startActivity(Intent(this, ReadStoriesActivity::class.java))
        }

        btn_draft.setOnClickListener {

        }

        btn_close.setOnClickListener {
            // Save your work
            // ...
            finish()
        }
    }
}
