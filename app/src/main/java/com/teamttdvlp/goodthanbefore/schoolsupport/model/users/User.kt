package com.teamttdvlp.goodthanbefore.schoolsupport.model.users

import com.google.firebase.firestore.Exclude

class User {
    var Id : String = ""
    var DisplayName:String = ""
    var LikedStories:ArrayList<String> = ArrayList()
    var PostedStories:ArrayList<String> = ArrayList()
    var Interests: ArrayList<String> = ArrayList()
    var Bookmarks: ArrayList<String> = ArrayList()
    var Email:String = ""
    var UserFollower : ArrayList<String> = ArrayList()
    var Avatar:String =""
    var JointDay :String =""
}