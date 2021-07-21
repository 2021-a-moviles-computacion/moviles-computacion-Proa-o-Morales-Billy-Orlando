package com.example.tarea_1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class IntentExplicitoParametros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent_explicito_parametros)

        val nombre = intent.getStringExtra("nombre")
        val apellido = intent.getStringExtra("apellido")
        val edad = intent.getIntExtra("edad", 0)
        val entrenador = intent.getParcelableExtra<Entrenador>("entrenador")

        Log.i("intent-explicito", "${nombre}")
        Log.i("intent-explicito", "${apellido}")
        Log.i("intent-explicito", "${edad}")
        Log.i("intent-explicito", "${entrenador}")

        val botonDevolverRespuesta = findViewById<Button>(R.id.btn_devolver_respuesta)

        botonDevolverRespuesta
            .setOnClickListener{
                val intentDevolverParametros = Intent()
                intentDevolverParametros.putExtra("nombreModificadp", "Orlando")
                intentDevolverParametros.putExtra("edadModificada", 22)
                intentDevolverParametros.putExtra(
                    "entrenadorModificado",
                    Entrenador("Nathaly", "Entrenadora de baile", null)
                )

                setResult(
                    RESULT_OK
                )
                finish()
                    this.finish()
            }
    }
}