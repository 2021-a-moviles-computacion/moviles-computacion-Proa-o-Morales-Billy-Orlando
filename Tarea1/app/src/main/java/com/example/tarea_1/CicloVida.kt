package com.example.tarea_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.service.autofill.TextValueSanitizer
import android.util.Log
import android.widget.Button
import android.widget.TextView

class CicloVida : AppCompatActivity() {


    var numero = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ciclo_vida)
        Log.i("ciclo-vida ", "onCcreate")

        val texViewCicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )

        texViewCicloVida.text = numero.toString()

        val botonCicloVida = findViewById<Button>(
            R.id.btn_aumentar_ciclo_vida
        )
        botonCicloVida.setOnClickListener{aumentarNumero()}
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            //AQUI SOLO GUARDAMOS PRIMITIVOS
            // CUALQUIER PRIMITIVO SOLO PRIITIVO
            putInt("numeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
        Log.i("ciclo-vida", "onSaveInstanceState")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado: Int? = savedInstanceState.getInt("numeroGuardado")

        if(numeroRecuperado != null){
            numero = numeroRecuperado
            val texViewCicloVida = findViewById<TextView>(
                R.id.txv_ciclo_vida
            )
            texViewCicloVida.text = numero.toString()
        }
        Log.i("ciclo-vida", "onRestoreInstanceState")
    }


   fun aumentarNumero(){
        numero = numero + 1
        val texViewCicloVida = findViewById<TextView>(
            R.id.txv_ciclo_vida
        )
        texViewCicloVida.text = numero.toString()
    }

    override fun onStart() {
        super.onStart()

        Log.i("ciclo-vida ", "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("ciclo-vida ", "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i("ciclo-vida ", "onResumen")
    }

    override fun onPause() {
        super.onPause()
        Log.i("ciclo-vida ", "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("ciclo-vida ", "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ciclo-vida ", "onDestroy")
    }

}