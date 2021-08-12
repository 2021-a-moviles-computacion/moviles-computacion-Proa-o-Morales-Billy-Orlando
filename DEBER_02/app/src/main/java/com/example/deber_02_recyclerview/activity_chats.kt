package com.example.deber_02_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class activity_chats : AppCompatActivity() {

    val mensajes = arrayListOf<Mensaje>(
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos"),
        Mensaje("hola como estas hace tiempo que no he sabido de ti", "Hola yo muy bien y tu es verdad hace tiempo que no hablamos")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)


        val contacto = intent.getParcelableExtra<Contacto>("contacto")

        //val txtMensaje = findViewById<TextView>(R.id.txt_chat_mensaje)
        val txtNombre = findViewById<TextView>(R.id.txt_chat_nombre)
        val img_chat = findViewById<ImageView>(R.id.img_chat)

        //txtMensaje.text = contacto!!.mensaje
        txtNombre.text = contacto!!.nombre
        Picasso.get().load(contacto!!.imagen).into(img_chat)

        irRecyclerView()

    }

    fun irRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_chats)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MensajeAdapter(mensajes)
        recyclerView.adapter = adapter

    }


}