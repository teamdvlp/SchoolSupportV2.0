package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories.process
import kotlin.random.Random

class SpawnStories {
    var count : Int = 0
    private var tagsEx : ArrayList<String> = arrayListOf("AndroidDevelopment", "IOSUI", "UI"
    , "Math", "Physics", "History", "PhysicalEducation", "SexEducation", "IT", "Literature", "Technology", "Chemistry"
    , "Biology", "Geography", "CivicEducation", "English", "EducationNews", "CompetitionNews")
    private fun spawnId () : ArrayList<String> {
        var result :ArrayList<String> = ArrayList()
        var a = Random.nextLong(9000000, 9999999999)
        for (i in 0..count) {
            a += i
            result.add(a.toString())
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