package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityLoginBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LoginViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var mBinding : ActivityLoginBinding
    private lateinit var mViewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addControls()
        addSetup()
        addEvents()
    }

    private fun addEvents() {

    }

    private fun addSetup() {
    }

    private fun addControls() {
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        mBinding.pgLogin.adapter = LoginViewPagerAdapter(supportFragmentManager)
    }
}
