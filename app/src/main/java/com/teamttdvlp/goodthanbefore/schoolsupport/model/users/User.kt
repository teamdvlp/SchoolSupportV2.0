package com.teamttdvlp.goodthanbefore.schoolsupport.model.users

import com.google.firebase.firestore.Exclude

class User {
    var id : String = ""
    var avatar = ""
    var displayName = ""
    @Exclude
    var interests : ArrayList<String> = ArrayList()
}