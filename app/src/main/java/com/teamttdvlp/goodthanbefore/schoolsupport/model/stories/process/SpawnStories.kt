package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process
import kotlin.random.Random

class SpawnStories {
    var count : Int = 0
    private var tagsEx : ArrayList<String> = arrayListOf("AndroidDevelopment", "IOSUI", "UI"
    , "Math", "Physics", "History", "PhysicalEducation", "SexEducation", "IT", "Literature", "Technology", "Chemistry"
    , "Biology", "Geography", "CivicEducation", "English", "EducationNews", "CompetitionNews")
    private fun spawnId () : ArrayList<String> {
        var result :ArrayList<String> = ArrayList()
        // sao đó cộng dần dần lên để đảm bảo id không trùng
        var start : Long = 100000000000
        for (i in 0..count) {
            start += i
            result.add(start.toString())
        }
        return result
    }

    private fun spawnTag () : ArrayList<String> {
        var result :ArrayList<String> = ArrayList()
        for (i in 0..count) {
            var index = Random.nextInt(0, tagsEx.size-1)
            result.add(tagsEx[index])
        }
        return result
    }

//    private fun spawnAvatar () : ArrayList<String> {
//
//    }
}