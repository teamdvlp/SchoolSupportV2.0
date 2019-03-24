package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityReadStoriesBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.DateSupport
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.ReadInfoEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.ReadStoriesViewModel
import java.lang.Exception

class ReadStoriesActivity : AppCompatActivity(), ReadInfoEvent {

    lateinit var mBinding : ActivityReadStoriesBinding

    lateinit var mViewModel : ReadStoriesViewModel
    lateinit var mAuthor : User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read_stories)
        setUp()
        addControls()
        addEvents()
    }

    private fun setUp () {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_read_stories)
        mViewModel = getViewModel()
        mViewModel.story = intent.getSerializableExtra("Story") as Stories
        Log.d("ReadStory", mViewModel.story.Title)

        mBinding.txtAuthorName.text = mViewModel.story.Author
        mBinding.txtBigTitle.text = mViewModel.story.Title
        mBinding.txtContent.fromHtml(mViewModel.story.Content)
        mBinding.txtPostTime.text = DateSupport.getDateByTimeMillis(mViewModel.story.PostedTime)
//        mViewModel.getAuthor(mViewModel.story.Author, this)
        Log.d("authorla", mViewModel.story.Author + "aaa")
    }

    override fun onReadInfoSuccess(user: User?) {
        mAuthor = user!!
        mBinding.txtAuthorName.text = mAuthor.DisplayName
    }

    override fun onReadInfoFailed(e: Exception?) {
        Toast.makeText(this, "Get Author Info failed", Toast.LENGTH_LONG).show()
        Log.d("ReadStories", "get Author failed ${e?.message}")
    }

    private fun addControls() {

    }

    private fun addEvents() {

    }
}
