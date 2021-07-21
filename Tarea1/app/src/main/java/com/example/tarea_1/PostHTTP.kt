package com.example.tarea_1

class PostHTTP(
    val id: Int,
    var userID: Any,
    val Title: String,
    val body: String) {

    init {
       if(userID is String){
           userID = (userID as String).toInt()
       }
       if(userID is Int){
           userID = userID
       }
    }
}