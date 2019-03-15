package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityUserBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.UserViewModel

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
        var mUser : User = intent.getSerializableExtra("User") as User
        mViewModel = getViewModel({ return@getViewModel UserViewModel(mUser)})
    }

    private fun addEvents() {
        mBinding.btnViewProfile.setOnClickListener({
            val intent = Intent(this, ViewProfileActivity::class.java)
            intent.putExtra("User", mViewModel.currentUser)
            startActivity(intent)
        })
        mBinding.btnCustomizeYourInterest.setOnClickListener {
            val intent = Intent(this, InterestActivity::class.java)
            intent.putExtra("User", mViewModel.currentUser)
            startActivity(intent)
        }
    }

    private fun setUp() {
        mBinding.txtUserName.text = mViewModel.currentUser.DisplayName
        if (!mViewModel.currentUser.Avatar.isNullOrEmpty())
        Picasso.get().load(mViewModel.currentUser.Avatar).into(mBinding.imgAvatar)
    }

}
