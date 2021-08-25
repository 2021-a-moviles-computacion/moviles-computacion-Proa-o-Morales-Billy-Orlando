package com.example.firebase

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
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
            val nuevoUsuario = hashMapOf<String, Any>(
                "roles" to rolesUsurio,
                "uid" to usuarioLogueado.uid
            )
            val identificadorUsuario = usuario.email
            db.collection("usuario")
            // el firestore nos asigna el identificador
                //.add(nuevoUsuario)
               //forma b en dodne se enera el id por parte del usuario
                .document(identificadorUsuario.toString())
                .set(nuevoUsuario)

                .addOnSuccessListener {
                    Log.i("fierbase-firestore", "Se creo")
                }
                .addOnFailureListener{
                    Log.i("firebase-firestore", "Fallo")
                }
        }else {
            Log.i("firebase-login", "ERROR !!")
        }
    }

}