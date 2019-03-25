package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.DialogSubmitStoryLayoutBinding

import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.PostNewStoryEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.notifiChanged
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.WriteStoriesActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.SubmitStoryViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.WriteStoriesViewModel
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.lang.Exception

class SubmitStoryFragment : Fragment(), PostNewStoryEvent, WriteStoriesActivity.OnActivityResultListener {
    private var mStory : Stories = Stories()
    private lateinit var mBinding : DialogSubmitStoryLayoutBinding
    private lateinit var mViewModel : SubmitStoryViewModel
    private lateinit var activittViewModel : WriteStoriesViewModel
    var currentAvatar : Bitmap? = null

    companion object {
        fun newInstance (date:String, author:String) : SubmitStoryFragment {
            var mDialog = SubmitStoryFragment()
            var bundle = Bundle()
            bundle.putString("Date", date)
            bundle.putString("Author", author)
            mDialog.arguments = bundle
            return mDialog
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_submit_story_layout, container, false)
        mBinding.lifecycleOwner = this
        mViewModel = getViewModel()
        activittViewModel = getViewModel()
        ((activity) as WriteStoriesActivity).activityResultListener = this
        Log.d("accc", "chayroido")
        mBinding.mVieModel = mViewModel
        mBinding.txtDate.text = mViewModel.getCurrentDate()
        mBinding.txtAuthor.text = CurrentUser.currentUser!!.DisplayName
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        addEvents()
    }

    private fun addEvents() {
        mBinding.btnCancel.setOnClickListener{
            activity!!.supportFragmentManager.popBackStack()
        }

        mBinding.imgAvatar.setOnClickListener {
            CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(requireActivity())
        }

        mBinding.btnSubmit.setOnClickListener{
            setUpStory()
            if (!checkData()) {
                Toast.makeText(context, "Please complete the information above", Toast.LENGTH_LONG).show()
            } else {
            Log.d("StoriesTest", "Title: ${mViewModel.currentStory.value!!.Title}")
            Log.d("StoriesTest", "Author: ${mViewModel.currentStory.value!!.Author}")
            Log.d("StoriesTest", "Tag: ${mViewModel.currentStory.value!!.Tag}")
            Log.d("StoriesTest", "Date: ${mViewModel.currentStory.value!!.PostedTime}")
            Log.d("StoriesTest", "Review: ${mViewModel.currentStory.value!!.Review}")
            Log.d("StoriesTest", "Content: ${mViewModel.currentStory.value!!.Content}")
            val bitmap  = (mBinding.imgAvatar.drawable as BitmapDrawable).bitmap
            mViewModel.postStory(this, bitmap)
            showProgressbar(true)
        }}
    }

    private fun checkData() : Boolean {
        var data  = mViewModel.currentStory.value!!
        if (data.Title.length < 16) {
            return false
        }
        if (data.Review.length < 26) {
            return false
        }
        if (data.Tag.length == 0) {
            return false
        }
        if (mBinding.imgAvatar.drawable== null) {
            return false
        }
        return true
    }

    override fun onActivityResultListener(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("avatarURi", "chay")
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val resultUri = result.uri
                Log.d("avatarURi", result.toString())
                mBinding.imgAvatar.setImageURI(resultUri)
                currentAvatar = ( mBinding.imgAvatar.drawable as BitmapDrawable).bitmap
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
                Log.e("loiroine ", "EditProfileActivity.kt \n onActivityResult \n $error")
            }
        }
    }

    fun showProgressbar (isLoading : Boolean) {
        if (isLoading) {
            mBinding.mainLayout.visibility = View.GONE
            mBinding.progresBar.visibility =  View.VISIBLE
        } else {
            mBinding.mainLayout.visibility = View.VISIBLE
            mBinding.progresBar.visibility = View.GONE
        }
    }

    override fun onPostNewStorySuccess() {
        showProgressbar(false)
        Toast.makeText(context, "Post Story Success", Toast.LENGTH_LONG).show()
        activity!!.supportFragmentManager.popBackStack()
    }

    override fun onPostNewStoryFailed(e: Exception?) {
        showProgressbar(false)
        Log.d("PostStory", "Failed: ${e?.message}")
        Toast.makeText(context, "Post Story Failed", Toast.LENGTH_LONG).show()
    }

    private fun addControls() {
    }

    private fun setUpStory () {
        mViewModel.currentStory.observe(this, object : Observer<Stories> {
            override fun onChanged(t: Stories?) {

            }

        })
        mViewModel.currentStory.value!!.PostedTime = System.currentTimeMillis()
        mViewModel.currentStory.value!!.Content = arguments?.getString("Content")!!
        mViewModel.currentStory.value!!.Author = CurrentUser.currentUser!!.DisplayName
        mViewModel.currentStory.notifiChanged()
        Log.d("SubmitStory", "content: " + mStory.Content)
    }

}