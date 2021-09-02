package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class DRestaurante : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drestaurante)
        val btnCrarRestaurante = findViewById<Button>(R.id.btn_crear_restaurante)

        btnCrarRestaurante.setOnClickListener {
            crearRestaurante()
        }
    }


    fun crearRestaurante(){
        val editTextNombre = findViewById<TextView>(R.id.txt_restaurante_nombre)
        val nuevorestaurante  = hashMapOf<String, Any>(
            "nombre" to editTextNombre.text.toString(),
        )

        val db = Firebase.firestore
        val referencia = db.collection("restaurante")
        referencia
            .add(nuevorestaurante)
            .addOnSuccessListener {
                editTextNombre.text = ""
            }
            .addOnFailureListener{
            }
    }
}