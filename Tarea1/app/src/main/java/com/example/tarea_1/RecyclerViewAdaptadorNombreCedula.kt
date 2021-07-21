package com.example.tarea_1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdaptadorNombreCedula(
    private val contexto: ActividadRecyclerView,
    private val listaEntrenador: List<Entrenador>,
    private val recyclerView: RecyclerView
): RecyclerView.Adapter<RecyclerViewAdaptadorNombreCedula.MyViewHolder>() {

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view){

        //cascaron para la logica de lo que vamos a crear
        val nombreTextView: TextView
        val cedulaTextView: TextView
        val likesTextView: TextView
        val acionBoton: Button
        var numeroLikes = 0

        init {
            nombreTextView = view.findViewById(R.id.txt_nombre)
            cedulaTextView = view.findViewById(R.id.txt_cedula)
            likesTextView = view.findViewById(R.id.txt_like)
            acionBoton = view.findViewById(R.id.bt_like)

            acionBoton.setOnClickListener {
                anadirLike()
            }
        }

        fun anadirLike(){
            this.numeroLikes = this.numeroLikes + 1
            likesTextView.text = this.numeroLikes.toString()
            contexto.aumentartotalLikes()
        }

    }

    //setear el layout que vamos a utillizar
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val intemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_view_vista,
                parent,
                false
            )

        return MyViewHolder(intemView)
    }

    //setear los datos de cada iteracion del arreglo
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //aqui empieza la vista del recyclerview es decir aqui se carga dinamicamente
        // la informacion
        val entrenador = listaEntrenador[position]
        holder.nombreTextView.text = entrenador.nombre
        holder.cedulaTextView.text = entrenador.descripcion
        holder.likesTextView.text = "0"
    }

    //setear el tamaño del arreglo 
    override fun getItemCount(): Int {
        return listaEntrenador.size
    }

}