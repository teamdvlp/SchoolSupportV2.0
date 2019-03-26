package com.teamttdvlp.goodthanbefore.schoolsupport.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.functions.FirebaseFunctions
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.adapter.LoginViewPagerAdapter
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.LoginViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.ActivityLoginBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.Stories
import com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process.SpawnStories
import com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment.FragmentLogin
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.HashMap

class LoginActivity : AppCompatActivity(), GoogleApiClient.OnConnectionFailedListener, FragmentLogin.OnButtonsClickListener {
    private lateinit var mBinding : ActivityLoginBinding

    private lateinit var mViewModel : LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        addControls()
        addSetup()
        addEvents()
    }

    override fun onSignUpButtonClick() {
        pgLogin.setCurrentItem(1, true)
    }


    private fun addEvents() {

    }

    private fun addSetup() {

    }


    override fun onConnectionFailed(p0: ConnectionResult) {
        mBinding.setLifecycleOwner (this)
    }

    private fun addControls() {
        mViewModel = getViewModel()
        mBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        mBinding.mViewModel = mViewModel
        mBinding.lifecycleOwner = this
        mBinding.pgLogin.adapter = LoginViewPagerAdapter(supportFragmentManager)
    }
}
