package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CProducto : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cproducto)

        val btnCrearProducto = findViewById<Button>(R.id.btn_Crear)
        btnCrearProducto.setOnClickListener {
            crearProducto()
        }
    }

    fun crearProducto(){
        val editTextNombre = findViewById<TextView>(R.id.txt_nombreProducto)
        val editTextPrecio = findViewById<TextView>(R.id.txtPrecioProducto)
        val nuevoProducto  = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
            "precio" to editTextPrecio.text.toString().toDouble()
        )

        val db = Firebase.firestore
        val referencia = db.collection("producto")
        referencia
            .add(nuevoProducto)
            .addOnSuccessListener {
                editTextNombre.text = ""
                editTextPrecio.text = ""
            }
            .addOnFailureListener{

            }
    }
}