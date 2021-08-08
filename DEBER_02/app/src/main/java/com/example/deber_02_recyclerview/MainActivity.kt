package com.example.deber_02_recyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESTA_INTENT_EXPLICITO = 400
    val contactos = listOf<Contacto>(
        Contacto("https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80",
            "Pablo",
            "hola como estas, no he sabido de ti hace mucho tiempo", "31 mar"),
        Contacto("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
            "Ximena",
            "hola como estas, no he sabido de ti hace mucho tiempo", "19 feb"),
        Contacto("https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80",
            "Carla",
            "hola como estas, no he sabido de ti hace mucho tiempo", "16:35"),
        Contacto("https://images.unsplash.com/photo-1552058544-f2b08422138a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=344&q=80",
        "Mario",
        "hola como estas, no he sabido de ti hace mucho tiempo", "09 jul"),
        Contacto("https://images.unsplash.com/photo-1504593811423-6dd665756598?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
            "Marco",
            "hola como estas, no he sabido de ti hace mucho tiempo", "17:25"),
        Contacto("https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80",
            "Pablo",
            "hola como estas, no he sabido de ti hace mucho tiempo", "31 mar"),
        Contacto("https://images.unsplash.com/photo-1438761681033-6461ffad8d80?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
            "Ximena",
            "hola como estas, no he sabido de ti hace mucho tiempo", "19 feb"),
        Contacto("https://images.unsplash.com/photo-1544005313-94ddf0286df2?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=334&q=80",
            "Carla",
            "hola como estas, no he sabido de ti hace mucho tiempo", "16:35"),
        Contacto("https://images.unsplash.com/photo-1552058544-f2b08422138a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=344&q=80",
            "Mario",
            "hola como estas, no he sabido de ti hace mucho tiempo", "09 jul"),
        Contacto("https://images.unsplash.com/photo-1504593811423-6dd665756598?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
            "Marco",
            "hola como estas, no he sabido de ti hace mucho tiempo", "17:25")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        irRecyclerView()

    }

    fun irRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLista)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactoAdapter(contactos)
        recyclerView.adapter = adapter

    }


}