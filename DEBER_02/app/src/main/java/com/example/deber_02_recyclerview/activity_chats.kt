package com.example.deber_02_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class activity_chats : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)


        val contacto = intent.getParcelableExtra<Contacto>("contacto")

        val txtMensaje = findViewById<TextView>(R.id.txt_chat_mensaje)
        val txtNombre = findViewById<TextView>(R.id.txt_chat_nombre)
        val img_chat = findViewById<ImageView>(R.id.img_chat)

        txtMensaje.text = contacto!!.mensaje
        txtNombre.text = contacto!!.nombre
        Picasso.get().load(contacto!!.imagen).into(img_chat)

    }
}