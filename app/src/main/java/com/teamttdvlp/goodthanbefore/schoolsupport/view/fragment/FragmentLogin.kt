package com.teamttdvlp.goodthanbefore.schoolsupport.view.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.GoogleApiClient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.databinding.FragmentLoginBinding
import com.teamttdvlp.goodthanbefore.schoolsupport.model.CurrentUser
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import com.teamttdvlp.goodthanbefore.schoolsupport.support.LogCode
import com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass.LoginEvent
import com.teamttdvlp.goodthanbefore.schoolsupport.support.getViewModel
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.InterestActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.MainActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.view.activity.OfflineToolActivity
import com.teamttdvlp.goodthanbefore.schoolsupport.viewmodel.LoginViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import java.util.*
import kotlin.collections.HashMap

class FragmentLogin : Fragment(), GoogleApiClient.OnConnectionFailedListener, FacebookCallback<LoginResult>, LoginEvent {
    private lateinit var mBinding : FragmentLoginBinding
    private lateinit var signinApi : GoogleApiClient
    private lateinit var mCallbackManager: CallbackManager
    private var permissonFacebook = Arrays.asList("email","public_profile")
    private lateinit var mFBLoginManager:com.facebook.login.LoginManager
    private val REQUESTCODE_GG_SIGNIN = 9

    private var onBtnSignUpClickListener : View.OnClickListener? = null

    private lateinit var activityViewModel : LoginViewModel
    @InternalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(layoutInflater, R.layout.fragment_login, container, false)
        addControls()
        setUp()
        addEvents()
        activityViewModel.isLoading.value = View.GONE
        if (!activityViewModel.keepMeLogin()) {
            activityViewModel.isLoading.value = View.VISIBLE
        }
        return mBinding.root
    }

    private fun addControls() {
        activityViewModel = activity!!.getViewModel()
        FirebaseFirestore.getInstance().collection("Stories23")
            .whereArrayContains("date", "07-03-2019")
            .get()
            .addOnCompleteListener {
                Log.d("Fuck", "thanh cong")
            }
    }

    private fun addEvents() {
        mBinding.btnLogin.setOnClickListener {
            activityViewModel.isLoading.value =  View.GONE
            val email = mBinding.edtEmail.text.toString()
            val password = mBinding.edtPassword.text.toString()
            activityViewModel.loginNormally(email, password)
        }

        mBinding.btnLoginGoogle.setOnClickListener {
            activityViewModel.isLoading.value =  View.GONE
            var intent = Auth.GoogleSignInApi.getSignInIntent(signinApi)
            startActivityForResult(intent, REQUESTCODE_GG_SIGNIN)
        }

        mBinding.btnLoginFacebook.setOnClickListener {
            activityViewModel.isLoading.value = View.GONE
            loginFacebook()
        }

        mBinding.btnWorkOffline.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }

        mBinding.btnSignup.setOnClickListener (onBtnSignUpClickListener)

    }

    fun setOnBtnSignUpClickListener (listener : (View) -> Unit) {
        onBtnSignUpClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                listener.invoke(v!!)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mCallbackManager.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE_GG_SIGNIN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                activityViewModel.loginWithGoogle(account!!)
            } catch (e: ApiException) {
                Result
                Log.w(LogCode.login, "Google sign in failed", e)
            }
        } else {
        }

    }

    private fun GGLoginSetup() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()

        signinApi = GoogleApiClient.Builder(context!!)
            .enableAutoManage(requireActivity(), this)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()
    }

    private fun setupLoginFacebook () {
        FacebookSdk.sdkInitialize(context)
        mCallbackManager = CallbackManager.Factory.create()
        mFBLoginManager = com.facebook.login.LoginManager.getInstance()
    }

    private fun loginFacebook () {
        mFBLoginManager.logInWithReadPermissions(this, permissonFacebook)
        mFBLoginManager.registerCallback(mCallbackManager, this)
    }

    // google login connection failed
    override fun onConnectionFailed(p0: ConnectionResult) {
        Log.w(LogCode.login, "GG connection failed: " + p0.errorMessage)
    }

    override fun onSuccess(result: LoginResult?) {
        activityViewModel.loginWithFacebook(result!!.accessToken)
    }

    override fun onCancel() {
        Log.w(LogCode.login, "FB connection was cancel")
    }

    override fun onLoginSuccess(user: User) {
        Log.d("LoginCC", user.Interests.size.toString())
        if (user.Interests.size == 0) {
            var intent = Intent(context, InterestActivity::class.java)
                intent.putExtra("User", user)
                startActivity(intent)
        }else {
            var intent = Intent(context, MainActivity::class.java)
            startActivity(intent)
        }
        CurrentUser.currentUser = user
        activity?.finish()
        Toast.makeText(context, "Login success", Toast.LENGTH_LONG)
    }

    override fun onLoginFailed(e: Exception?) {
        activityViewModel.isLoading.value =  View.VISIBLE
        Toast.makeText(context, "Login failed", Toast.LENGTH_LONG)
    }

    override fun onError(error: FacebookException?) {
        Log.w(LogCode.login, "FB connection failed: " + error!!.message)
    }

    private fun setUp() {
        GGLoginSetup()
        setupLoginFacebook()
        activityViewModel.onLoginEvent = this
    }
    companion object {
        private val mInstance : FragmentLogin = FragmentLogin()
        fun getInstance () : FragmentLogin {
            return mInstance
        }
    }
}
