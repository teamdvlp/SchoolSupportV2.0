package com.teamttdvlp.goodthanbefore.schoolsupport.presenter.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.teamttdvlp.goodthanbefore.schoolsupport.R
import com.teamttdvlp.goodthanbefore.schoolsupport.data.entities.data.DataAction
import com.teamttdvlp.goodthanbefore.schoolsupport.data.entities.data.DataContext
import com.teamttdvlp.goodthanbefore.schoolsupport.model.users.User

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        var param1 : User = User()
        param1.About = "asdasd"
        param1.Avatar = "asdasd"
        param1.Interests = arrayListOf("123", "912001", "asdasd")
        var action1 = DataAction(
            "tien",
            hashMapOf(Pair("minhtien", "tien"), Pair("minhtien2", "912001"))
        )
        var action2 = DataAction(
            "tien",
            hashMapOf(Pair("ohyeah", param1))
        )
        var context = DataContext()
        context.addAction(action1)
        context.addAction(action2)
        Log.d("jsonData", context.toJson()
        )
    }
}
