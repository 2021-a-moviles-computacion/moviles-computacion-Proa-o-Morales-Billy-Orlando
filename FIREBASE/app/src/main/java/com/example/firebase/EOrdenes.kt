package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.firebase.dto.FirestoreProductoDTO
import com.example.firebase.dto.FirestoreRestauranteDTO
import com.example.firebase.dto.OrdenDTO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EOrdenes : AppCompatActivity() {

    var arregloProductos = arrayListOf<FirestoreProductoDTO>()
    var adapterProductos: ArrayAdapter<FirestoreProductoDTO>? = null
    var produtcSeleccionado: FirestoreProductoDTO? = null

    var arregloRestaurantes = arrayListOf<FirestoreRestauranteDTO>()
    var adapterRestaurante: ArrayAdapter<FirestoreRestauranteDTO>? = null
    var restauranteSeleccionado: FirestoreRestauranteDTO? = null

    var arregloOrdenes = arrayListOf<OrdenDTO>()


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
        //CARGAR PRODUCTOS AL LIST VIEW
        val btnAnadir = findViewById<Button>(R.id.btn_anadir_lista_productos)
        btnAnadir.setOnClickListener {
            cargarListaDeProductos()
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
                    restaurante!!.nombreRestaurante = document.get("nombre").toString()

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
                        producto!!.nombreCProducto = document.get("nombre").toString()
                        producto!!.precioProductp = document.get("precio").toString().toDouble()
                        arregloProductos.add(producto)
                        adapterProductos?.notifyDataSetChanged()
                    }
                }
                .addOnFailureListener {
                    Log.i("firebase-firestore", "no se cargaron los spiner")
                }
    }

    fun cargarListaDeProductos(){

        val listViewProductos = findViewById<ListView>(R.id.list_view_productos)
        val adaptadorOrden = ArrayAdapter(this,android.R.layout.simple_selectable_list_item, arregloOrdenes)
        val txtCantidad = findViewById<TextView>(R.id.txt_cantidad)
        val txtTotal = findViewById<TextView>(R.id.txt_total)

        var precioUnitario: Double = produtcSeleccionado!!.precioProductp
        val cantidad = txtCantidad.text.toString().toInt()
        var totalUnitario = precioUnitario * cantidad
        val totalMostar = String.format("%.2f", totalUnitario)
        var totalOrden = 0.0



        listViewProductos.adapter = adaptadorOrden

        arregloOrdenes.add(OrdenDTO(produtcSeleccionado!!.nombreCProducto, produtcSeleccionado!!.precioProductp, txtCantidad.text.toString().toInt(), totalMostar.toDouble()))

        txtCantidad.text = ""

        if(arregloOrdenes != null){
            totalOrden  = arregloOrdenes.map { it!!.totalUnitario}
                .fold(0.0) { acumulador, totalUnitarioSuma -> (acumulador + totalUnitarioSuma) }.toDouble()
        }

        txtTotal.text = "Total Orden $${totalOrden.toString()}"
        adaptadorOrden.notifyDataSetChanged()
    }

}