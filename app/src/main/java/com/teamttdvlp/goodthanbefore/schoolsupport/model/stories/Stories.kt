package com.teamttdvlp.goodthanbefore.schoolsupport.model.stories
class Stories {
    // spawn
    var Avatar : String =""
    var Content : String =""
    // spawn
    var Id : String =""
    // spawn
    var Tag : String = ""
    // spawn từ tháng 1 đến tháng 3
    var PostedTime : Long = 0
    // spawn
    var Topic : String =""
    // spawn
    var Claps : Int = 0
    // spawn
    var Views : Int = 0
    // spawn: điểm này spawn giá trị từ 0 -> 100
    var Reputation : Int = 0
    // spawn: Đây là số lượng điểm Hot trong 3 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var ThreeHotDayCycle : Int= 0
    // spawn: Đây là số lượng điểm Hot trong 5 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var FiveHotDayCycle : Int= 0
    // spawn: Đây là số lượng điểm Hot trong 7 ngày, chỉ cần spawn ngẫu nhiên là được từ 200 -> 1000
    var SevenHotDayCycle : Int= 0
    // spawn
    var Title : String = ""
    // spawn
    var Author :String = ""
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var ThreeHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var FiveHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var SevenHotDayLifeCycle : ArrayList<String> = ArrayList()
    // spawn: chỉ cần random ngày bắt đầu (từ tháng 1 đến tháng 3, sau đó cộng lên)
    var ReputationLifeCycle : ArrayList<String> = ArrayList()
    // spawn: random từ 1 đến 4
    // nếu là 1 -> isRep=true, is Hot=false
    // nếu là 2 -> isRep=true, is Hot = true
    // nếu là 3 -> isRep = false, isHot = true
    //nêu slà 4 -> isRep = false, isHot=false
    var isRep = false
    var isHot = false
}