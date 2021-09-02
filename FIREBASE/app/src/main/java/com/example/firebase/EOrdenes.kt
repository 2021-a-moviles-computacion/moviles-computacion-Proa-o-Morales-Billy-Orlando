package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.firebase.dto.FirestoreProductoDTO
import com.example.firebase.dto.FirestoreRestauranteDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {

    var arregloProductos = arrayListOf<FirestoreProductoDTO>()
    var adapterProductos: ArrayAdapter<FirestoreProductoDTO>? = null
    var produtcSeleccionado: FirestoreProductoDTO? = null

    var arregloRestaurantes = arrayListOf<FirestoreRestauranteDTO>()
    var adapterRestaurante: ArrayAdapter<FirestoreRestauranteDTO>? = null
    var restauranteSeleccionado: FirestoreRestauranteDTO? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eordenes)

        // cargar spinner de restaurantes
        if(adapterRestaurante == null){

            adapterRestaurante = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloRestaurantes
            )
            adapterRestaurante?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarRestaurantes()
        }

        //caragr spiner de productos
        if(adapterProductos == null){

            adapterProductos = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                arregloProductos
            )
            adapterProductos?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            cargarProductos()
        }
    }


    fun cargarRestaurantes(){
        val spinerRestaurante = findViewById<Spinner>(R.id.sp_restaurante)
        spinerRestaurante.adapter = adapterRestaurante
        spinerRestaurante.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long)
                {
                    restauranteSeleccionado = arregloRestaurantes[position]
                }

            override fun onNothingSelected(parente: AdapterView<*>?) {
                Log.i("firebase-firestore", "No ha seleccionado ningun item")
            }
        }

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")

        referencia
            .get()
            .addOnSuccessListener {
                for (document in it){
                    var restaurante = document.toObject(FirestoreRestauranteDTO::class.java)
                    restaurante!!.uid = document.id
                    arregloRestaurantes.add(restaurante)
                    adapterRestaurante?.notifyDataSetChanged()
                }
            }
            .addOnFailureListener {

            }
    }

    fun cargarProductos(){
        val spinerProducto = findViewById<Spinner>(R.id.sp_producto)
        spinerProducto.adapter = adapterProductos
        spinerProducto.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long) {
                produtcSeleccionado = arregloProductos[position]
            }

            override fun onNothingSelected(parente: AdapterView<*>?) {
                Log.i("firebase-firestore", "No ha seleccionado ningun item")
            }
        }

        val db = Firebase.firestore
        val referencia = db.collection("producto")

            referencia
                .get()
                .addOnSuccessListener {
                    for (document in it){
                        var producto = document.toObject(FirestoreProductoDTO::class.java)
                        producto!!.uid = document.id

                        arregloProductos.add(producto)
                        adapterProductos?.notifyDataSetChanged()
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "no se cargaron los spiner")
                }
    }


}