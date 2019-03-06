package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentSignUpBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.LoginViewModel

class FragmentSignUp : Fragment() {
    private lateinit var mBinding : FragmentSignUpBinding
    private lateinit var activityModel : LoginViewModel

    private var isEmailValid = false
    private var isPasswordValid = true
    private var isRepeatPassValidValid = true
    private var isDisplayNameValid = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_sign_up, container, false)
        activityModel = activity!!.getViewModel()
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        addControls()
        setup()
        addEvents()
    }

    private fun addEvents() {
        mBinding.btnSignupOk.setOnClickListener{
            val email = mBinding.edtEmail.text.toString()
            val password = mBinding.edtPassword.text.toString()
            val displayName = mBinding.edtDisplayname.text.toString()
            activityModel.signup(email, password, displayName)
        }
        mBinding.edtEmail.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                isEmailValid = activityModel.checkEmail(s.toString())
                mBinding.rbtnEmailValid.isChecked = isEmailValid
                mBinding.rbtnEmailValid.visibility = View.VISIBLE
                resetButton()
                if (!isEmailValid) {
                    mBinding.txtErrMessEmailInValid.visibility = View.VISIBLE
                } else {
                    mBinding.txtErrMessEmailInValid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        mBinding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isPasswordValid = activityModel.checkPassword(s.toString())
                mBinding.rbtnPasswordValid.isChecked = isPasswordValid
                mBinding.rbtnPasswordValid.visibility = View.VISIBLE
                resetButton()
                if (!isPasswordValid) {
                    mBinding.txtErrMessPasswordInValid.visibility = View.VISIBLE
                } else {
                    mBinding.txtErrMessPasswordInValid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        mBinding.edtDisplayname.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var isDisplayNameValid = activityModel.checkDisplayname(s.toString())
                mBinding.rbtnDisplayNameValid.isChecked = isDisplayNameValid
                mBinding.rbtnDisplayNameValid.visibility = View.VISIBLE
                resetButton()
                if (!isDisplayNameValid) {
                    mBinding.txtErrMessDisplayNameInValid.visibility = View.VISIBLE
                } else {
                    mBinding.txtErrMessDisplayNameInValid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
        mBinding.edtRepeatPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                isRepeatPassValidValid = mBinding.edtPassword.text.toString().equals(mBinding.edtRepeatPassword.text.toString())
                mBinding.rbtnRepeatPasswordValid.isChecked = isRepeatPassValidValid
                mBinding.rbtnRepeatPasswordValid.visibility = View.VISIBLE
                resetButton()
                if (!isRepeatPassValidValid) {
                    mBinding.txtErrMessRepeatPasswordInValid.visibility = View.VISIBLE
                } else {
                    mBinding.txtErrMessRepeatPasswordInValid.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })


    }

    private fun resetButton () {
        if (isEmailValid && isDisplayNameValid && isPasswordValid && isRepeatPassValidValid) {
            mBinding.btnSignUpDefault.visibility = View.GONE
            mBinding.btnSignupOk.visibility = View.VISIBLE
        } else {
            mBinding.btnSignUpDefault.visibility = View.VISIBLE
            mBinding.btnSignupOk.visibility = View.GONE
        }
    }

    private fun setup() {
    }

    private fun addControls() {
    }

    companion object {
        private val mInstance : FragmentSignUp = FragmentSignUp()
        fun getInstance () : FragmentSignUp {
            return mInstance
        }

    }
}
