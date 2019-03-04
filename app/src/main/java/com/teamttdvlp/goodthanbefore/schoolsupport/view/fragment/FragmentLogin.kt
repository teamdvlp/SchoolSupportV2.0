package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentLoginBinding


class FragmentLogin : Fragment() {
    private lateinit var mBinding : FragmentLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        addControls()
        setup()
        addEvents()
        return mBinding.root

    }

    private fun addControls() {
    }

    private fun addEvents() {
        mBinding.btnSignup.setOnClickListener({

        })
    }

    private fun setup() {

    }
    companion object {
        private val mInstance : FragmentLogin = FragmentLogin()
        fun getInstance () : FragmentLogin {
            return mInstance
        }
    }
}
