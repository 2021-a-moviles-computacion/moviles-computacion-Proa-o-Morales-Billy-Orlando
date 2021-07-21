package com.example.tarea_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ActividadRecyclerView : AppCompatActivity() {

    var totalLikes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_recycler_view)

        val listaEntrenador = arrayListOf<Entrenador>()
        val liga = Liga("Barrial", "colinar del norte")
        listaEntrenador
            .add(
                Entrenador("Billy",
                    "1725996365",
                    liga)
            )

        val recyclerViewEntrenador = findViewById<RecyclerView>(
            R.id.recy_entrenador
        )

        this.iniciarRecyclerView(
            listaEntrenador,
            this,
            recyclerViewEntrenador
        )
    }

    fun iniciarRecyclerView(
        lista: List<Entrenador>,
        actividad: ActividadRecyclerView,
        recyclerView: RecyclerView
    ){
        val adaptador = RecyclerViewAdaptadorNombreCedula(
            actividad,
            lista,
            recyclerView
        )
        recyclerView.adapter = adaptador
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(actividad)
        adaptador.notifyDataSetChanged()

    }

    fun aumentartotalLikes(){

        totalLikes = totalLikes + 1
        val textview  = findViewById<TextView>(R.id.txt_like_act_recy)
        textview.text = totalLikes.toString()
    }




}

