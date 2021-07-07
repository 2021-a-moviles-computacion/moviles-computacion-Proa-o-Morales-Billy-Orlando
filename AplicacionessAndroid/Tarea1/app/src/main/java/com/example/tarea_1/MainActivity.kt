package com.example.tarea_1

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    val CODIGO_RESPUESYA_INTENT_EXPLICITO = 401
    val CODIGO_RESPUESYA_INTENT_IMPLICITO = 402

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BaseDatos.tablaUsuario = SqliteHelPerUsiario(this)

        //trabajo en clase

        val botonAbrirBaseDatos = findViewById<Button>(R.id.btn_abrir_bdd)

        botonAbrirBaseDatos.
                setOnClickListener{
                    abrirActiviad(UsoBaseDatos::class.java)
                }


        val btCicloVida = findViewById<Button>(
            R.id.bt_CicloVida
        )
        btCicloVida
            .setOnClickListener{
                abrirActiviad(CicloVida::class.java)
            }

        val btListView = findViewById<Button>(
            R.id.btn_list_view
        )
        btListView.
                setOnClickListener{
                    abrirActiviad(ListView::class.java)
                }

        val btnIntent = findViewById<Button>(
            R.id.btn_ir_activity
        )
        btnIntent.
        setOnClickListener{
            abrirActividadConParametros(IntentExplicitoParametros::class.java)
        }

        val btnIntentImplicito = findViewById<Button>(
            R.id.btn_intent_inplicito
        )

        btnIntentImplicito.
                setOnClickListener{
                    val intentImplicito = Intent(
                        Intent.ACTION_PICK,
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    )

                    startActivityForResult(intentImplicito, CODIGO_RESPUESYA_INTENT_IMPLICITO)
                }
    }

    fun abrirActiviad(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )

        startActivity(intentExplicito)
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ){
        val intentExplicito = Intent(
            this,
            clase
        )
        intentExplicito.putExtra("nombre", "Billy")
        intentExplicito.putExtra("apellido", "Proaño")
        intentExplicito.putExtra("edad", 24)
        intentExplicito.putExtra(
            "entrenador",
            Entrenador("Billy", "Entrenador de futbol", null)
        )

        startActivityForResult(intentExplicito, CODIGO_RESPUESYA_INTENT_EXPLICITO)

        /*
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            when(it.resultCode){
                Activity.RESULT_OK-> {
                    //Ejecutar codigo OK
                    it.data?.getStringExtra("nombreModofocado")
                    it.data?.getIntExtra("edadModificada",0)
                    it.data?.getParcelableExtra<Entrenador>("entrenadorModificado")
                }
            }
        }

         */
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){

            //CODIGO PARAMETROS EXPLICITOS
            CODIGO_RESPUESYA_INTENT_EXPLICITO -> {
                if(resultCode == RESULT_OK){
                    Log.i("intent-explicito", "Si actualizao los datos")
                    if(data != null){
                        val nombre = data.getStringExtra("nombreModificadp")
                        val edad = data.getIntExtra("edadModificada",0)
                        val entrenador = data.getParcelableExtra<Entrenador>("entrenadorModificado")

                        Log.i("intent-explicito", "${nombre}")
                        Log.i("intent-explicito", "${edad}")
                        Log.i("intent-explicito", "${entrenador}")
                    }
                }
            }
            //CODIGO PARAMETROS IMPLICITOS
            CODIGO_RESPUESYA_INTENT_IMPLICITO -> {
                if(resultCode == RESULT_OK){
                    if(data != null){
                        if(data.data != null){
                            val uri: Uri = data.data!!
                            val cursor = contentResolver.query(
                                uri,
                                null,
                                null,
                                null,
                                null,
                                null
                            )
                            cursor?.moveToFirst()
                            val indiceTelefono = cursor?.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                            )
                            val telefono = cursor?.getString(
                                indiceTelefono!!
                            )
                            cursor?.close()
                            Log.i("resultado", "telefono${telefono}")
                        }
                    }
                }
            }
        }
    }


}