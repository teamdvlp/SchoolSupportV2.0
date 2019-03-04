package com.teamttdvlp.goodthanbefore.schoolsupport.interfaces.account

import com.google.android.gms.auth.api.signin.GoogleSignInAccount

interface ILoginWithGoogle {
    fun login (account:GoogleSignInAccount)
}