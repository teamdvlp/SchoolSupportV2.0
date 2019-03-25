package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.WriteStoriesViewModel
import kotlinx.android.synthetic.main.activity_write_stories.*
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityWriteStoriesBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView

class WriteStoriesActivity : AppCompatActivity() {
    lateinit var mViewModel : WriteStoriesViewModel
    lateinit var mViewBinding : ActivityWriteStoriesBinding
    lateinit var activityResultListener: WriteStoriesActivity.OnActivityResultListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_stories)
        addControls()
        mViewModel = getViewModel()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        activityResultListener.onActivityResultListener(requestCode, resultCode, data)
//    }

    private fun addControls() {

    }

    interface OnActivityResultListener {
        fun onActivityResultListener (requestCode: Int, resultCode: Int, data: Intent?)
    }
}
