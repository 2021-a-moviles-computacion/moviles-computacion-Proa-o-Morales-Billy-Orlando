package com.example.tarea_1

import android.media.Session2Command
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.beust.klaxon.Klaxon
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.result.Result

class UsarProtocoloHTTP : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_usar_protocolo_http)
       // metodoGet()
        metodoPost()
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
                        Log.i("http-klaxon", "ERROR: ${getString}")

                        //cuando recibio sun unico elemento
                        val post = Klaxon()
                            .parse<PostHTTP>(getString)
                        Log.i("http-klaxon", "${post?.body}")

                    }
                }
            }
    }

    fun metodoPost(){
        val parametros: List<Pair<String, *>> = listOf(
            "title" to "Titulo moviles",
            "body" to "Descripcion moviles",
            "userID" to 1
        )

        "https://jsonplaceholder.typicode.com/posts"
            .httpPost(parametros)
            .responseString { request, response, result ->
                when(result){
                    is Result.Failure -> {
                        val error = result.getException()
                        Log.i("http-klaxon", "Error ${error}")
                    }
                    is Result.Success -> {
                        val postString = result.get()
                        Log.i("http-klaxon", "${postString}")

                        val post = Klaxon()
                            .parse<PostHTTP>(postString)
                        Log.i("http-klaxon", "${post?.userID}")
                    }
                }
            }
    }


}