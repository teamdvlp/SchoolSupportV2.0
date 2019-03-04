package com.teamttdvlp.goodthanbefore.schoolsupport.support.dataclass

import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User
import java.lang.Exception

data class LoginEvent (var onSuccess : (User)->Unit, var onFailed : (Exception))