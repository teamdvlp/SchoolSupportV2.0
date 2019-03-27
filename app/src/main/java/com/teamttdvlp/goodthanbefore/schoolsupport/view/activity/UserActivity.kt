package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityUserBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.support.logError
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityUserBinding
    private lateinit var mViewModel:UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_user)
        addControls()
        addEvents()
        setUp()
    }

    private fun addControls() {
        mViewModel = getViewModel ()
    }

    override fun onResume() {
        super.onResume()
        if (CurrentUser.bitmapUserAvatar != null)
        imgAvatar.setImageBitmap(CurrentUser.bitmapUserAvatar)
        txtUserName.text = CurrentUser.currentUser?.DisplayName
    }

    private fun addEvents() {
        mBinding.btnViewProfile.setOnClickListener {
            val intent = Intent(this, ViewProfileActivity::class.java)
            intent.putExtra("User", CurrentUser.currentUser)
            startActivity(intent)
        }

        mBinding.btnCustomizeYourInterest.setOnClickListener {
            val intent = Intent(this, InterestActivity::class.java)

            intent.putExtra("User",CurrentUser.currentUser)
            startActivity(intent)
        }
        mBinding.localTxtSignOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            var i = Intent(this, LoginActivity::class.java)
            finishAffinity()
            startActivity(i)
            CurrentUser.currentUser = null
        }
    }

    private fun setUp() {
        mBinding.txtUserName.text =CurrentUser.currentUser!!.DisplayName
    }

}
