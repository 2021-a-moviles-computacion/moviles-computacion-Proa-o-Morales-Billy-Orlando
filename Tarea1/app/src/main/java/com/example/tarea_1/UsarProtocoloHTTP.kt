package com.example.tarea_1

import android.media.Session2Command
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result

class UsarProtocoloHTTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usar_protocolo_http)
        metodoGet()
    }

    fun metodoGet(){
        "https://jsonplaceholder.typicode.com/posts/1"
            .httpGet()
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "ERROR: ${error}")
                    }
                    is Result.Success ->{
                        val getString = result.get()
                        Log.i("http-klaxon", "ERROR: ${getString    }")
                    }
                }
            }
    }


}