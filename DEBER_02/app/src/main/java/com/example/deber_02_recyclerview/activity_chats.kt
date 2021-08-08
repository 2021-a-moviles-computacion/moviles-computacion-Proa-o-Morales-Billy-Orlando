package com.example.deber_02_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text

class activity_chats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)


        val contacto = intent.getParcelableExtra<Contacto>("contacto")

        val txtMensaje = findViewById<TextView>(R.id.txt_chat_mensaje)
        val txtNombre = findViewById<TextView>(R.id.txt_chat_nombre)

        txtMensaje.text = contacto!!.mensaje
        txtNombre.text = contacto!!.nombre

    }
}