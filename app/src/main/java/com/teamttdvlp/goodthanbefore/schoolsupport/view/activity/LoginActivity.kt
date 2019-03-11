package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LoginViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.LoginViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener {

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


    override fun onConnectionFailed(p0: ConnectionResult) {

    }

    private fun addControls() {
        mViewModel = getViewModel()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        mBinding.mViewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.pgLogin.adapter = LoginViewPagerAdapter(supportFragmentManager)
    }
}
