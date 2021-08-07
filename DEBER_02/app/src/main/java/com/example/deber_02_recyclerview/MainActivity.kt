package com.example.deber_02_recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val contactos = listOf<Contacto>(
        Contacto("https://images.unsplash.com/photo-1547425260-76bcadfb4f2c?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=1500&q=80",
            "Pablo",
            "hola como estas, no he sabido de ti hace mucho tiempo")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        irRecyclerView()

    }

    fun irRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLista)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactoAdapter(contactos)
        recyclerView.adapter = adapter

    }
}