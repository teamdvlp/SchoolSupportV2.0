package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.firebase.firestore.FirebaseFirestore
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.users.process.IUserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.CompactStory
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.SpawnStories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.UserStoriesManger
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.process.UserLikedStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.GetMultipleStories
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    lateinit var mainNavHost : NavController
    lateinit var mViewModel : MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addControls()
        addEvents()
//        var arr = SpawnStories().spawnStories(10)
//        for (story in arr) {
//            FirebaseFirestore.getInstance().collection("Stories").document(story.Id)
//                .set(story)
//        }
    }

    private fun addControls() {
        mViewModel = getViewModel()
        mainNavHost = Navigation.findNavController(this, R.id.main_nav_host)
    }

    private fun addEvents() {
        rbtn_global.setOnCheckedChangeListener (this)
        rbtn_local.setOnCheckedChangeListener (this)
        rbtn_tool.setOnCheckedChangeListener (this)
        rbtn_bookmarks.setOnCheckedChangeListener (this)
        rbtn_new_stories.setOnClickListener {
            startActivity(Intent(this, WriteStoriesActivity::class.java))
        }
    }

    override fun onCheckedChanged(view: CompoundButton?, isChecked: Boolean) {
        if (isChecked) {
            val navigatingFragmentId : Int? = when (view?.id) {
                R.id.rbtn_global -> R.id.main_nav_fragment_global
                R.id.rbtn_local -> R.id.main_nav_fragment_local
                R.id.rbtn_tool -> R.id.main_nav_fragment_tool
                R.id.rbtn_bookmarks -> R.id.main_nav_fragment_bookmarks
                else -> null
            }
            if (navigatingFragmentId != null) mainNavHost.navigate(navigatingFragmentId)
        }
    }

}
