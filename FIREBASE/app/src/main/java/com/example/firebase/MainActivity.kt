package com.example.firebase

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.BadParcelableException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.firebase.dto.FirestoreUsuarioDTO
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val CODIGO_INICIO_SESION = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_login = findViewById<Button>(R.id.bt_login)

        btn_login.setOnClickListener {
            llamarLoguinUsuario()
        }

        val btn_logout = findViewById<Button>(R.id.btn_logout)
        btn_logout.setOnClickListener {
            solicitarSalirAplicativo()
        }

        val btn_producto = findViewById<Button>(R.id.bt_producto)
        btn_producto.setOnClickListener {
            val intent = Intent(
                this,
                CProducto::class.java
            )
            startActivity(intent)
        }

        val btn_restaurante = findViewById<Button>(R.id.btn_restauramte)

        btn_restaurante.setOnClickListener {
            val intent = Intent(
                this,
                DRestaurante::class.java
            )
            startActivity(intent)
        }

        val btn_ordenes = findViewById<Button>(R.id.btn_ordenes)
        btn_ordenes.setOnClickListener {
            val intent = Intent(
                this,
                EOrdenes::class.java
            )
            startActivity(intent)
        }

    }

    fun llamarLoguinUsuario(){
        //llmar al login del usuario
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder().
                setAvailableProviders(providers).
                setTosAndPrivacyPolicyUrls(
                "https:://example.com/terms.html",
                "https://example.com/privacy.html"
            ).build(),
            CODIGO_INICIO_SESION
        )

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            CODIGO_INICIO_SESION -> {
                if(resultCode == Activity.RESULT_OK){
                    val usuario = IdpResponse.fromResultIntent(data)
                    if ( usuario != null ){
                        if(usuario.isNewUser == true){
                            Log.i("firebase-login", "Nuevo usuario")
                            registrarUsuarioPrimeraVez(usuario)
                        }else {
                            setearUsuarioFirebase()
                            Log.i("firebase-login", "Nuevo antiguo")
                        }
                    }
                }else {
                    Log.i("firebase-login", "El usuario cancelo")
                }
            }
        }
    }

    fun registrarUsuarioPrimeraVez(usuario: IdpResponse){
        //obtener el ID del usuario
        val usuarioLogueado = FirebaseAuth
            .getInstance()
            .getCurrentUser()

        if(usuario.email != null && usuarioLogueado != null){
            // roles : ["usuario : "admin" ]
            val db = Firebase.firestore
            val rolesUsurio = arrayListOf("usuario")
            val identificadorUsuario = usuario.email
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsurio,
                "uid" to usuarioLogueado.uid,
                "email" to identificadorUsuario.toString()
            )

            db.collection("usuario")
            // el firestore nos asigna el identificador
                //.add(nuevoUsuario)
               //forma b en dodne se enera el id por parte del usuario  
                .document(identificadorUsuario.toString())
                .set(nuevoUsuario)

                .addOnSuccessListener {
                    Log.i("fierbase-firestore", "Se creo")
                    setearUsuarioFirebase()
                }
                .addOnFailureListener{
                    Log.i("firebase-firestore", "Fallo")
                }
        }else {
            Log.i("firebase-login", "ERROR !!")
        }
    }

    fun setearUsuarioFirebase(){
        val instanciaAuth = FirebaseAuth.getInstance()
        val usuarioLocal = instanciaAuth.currentUser
        if(usuarioLocal != null){
            if(usuarioLocal.email != null){
                val db = Firebase.firestore

                val referencia = db
                    .collection("usuario")
                    .document(usuarioLocal.email.toString())

                referencia
                    .get()
                    .addOnSuccessListener {
                        val usuarioCargado: FirestoreUsuarioDTO? = it.toObject(FirestoreUsuarioDTO::class.java)
                        if(usuarioCargado != null){
                            BAuthUsuario.usuario = BUsuarioFirebase(
                                usuarioCargado.uid,
                                usuarioCargado.email,
                                usuarioCargado.roles
                            )
                            setearBienvenida()
                            Log.i("firebase-firestore", "Usuario cargado")
                        }

                    }
                    .addOnFailureListener{
                        Log.i("firebase-firestore", "Fallo cargar usuario")
                    }
            }
        }
    }

    fun setearBienvenida(){
        val textBienvenida = findViewById<TextView>(R.id.txt_Bienvenida)
        val btn_login = findViewById<Button>(R.id.bt_login)
        val btn_logout =  findViewById<Button>(R.id.btn_logout)
        val btn_producto = findViewById<Button>(R.id.bt_producto)
        val btn_restaurantes = findViewById<Button>(R.id.btn_restauramte)
        val btn_ordenes = findViewById<Button>(R.id.btn_ordenes)
        if(BAuthUsuario.usuario != null){
            textBienvenida.text = "Bienvenido usuario ${BAuthUsuario.usuario?.email}"
            btn_login.visibility = View.INVISIBLE
            btn_logout.visibility = View.VISIBLE
            btn_ordenes.visibility = View.VISIBLE
            btn_producto.visibility = View.VISIBLE
            btn_restaurantes.visibility = View.VISIBLE

        }else{
            textBienvenida.text = "Ingresa al aplicativo"
            btn_login.visibility = View.VISIBLE
            btn_logout.visibility = View.INVISIBLE
            btn_ordenes.visibility = View.INVISIBLE
            btn_producto.visibility = View.INVISIBLE
            btn_restaurantes.visibility = View.INVISIBLE
        }
    }

    fun solicitarSalirAplicativo(){
        AuthUI
            .getInstance()
            .signOut(this)
            .addOnCompleteListener{
                BAuthUsuario.usuario = null
                setearBienvenida()
            }
    }



}