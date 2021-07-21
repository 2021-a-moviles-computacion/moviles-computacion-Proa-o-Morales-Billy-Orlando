package com.example.examen01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ir_aplicacion = findViewById<ImageView>(R.id.img_view_cms)

        ir_aplicacion.setOnClickListener {
            irAṕlicacion()
        }
    }

    fun irAṕlicacion() {
        val intentExplicito = Intent(
            this,
            EmpresaActivity1::class.java
        )
        startActivity(intentExplicito)

    }

}

