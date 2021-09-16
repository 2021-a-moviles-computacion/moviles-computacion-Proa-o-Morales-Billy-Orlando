package com.example.examen01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
/*
class BaseDatos(
    contexto: Context?
) : SQLiteOpenHelper(
    contexto,
    "examen01",
    null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptCrearTablaEmpresa =
            """
                CREATE TABLE EMPRESA (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                ruc VARCHAR (13),
                razonSocial varchar (50),
                direccion varchar (50),
                telefono INTEGER)
                
            """.trimIndent()
        val scriptCrearTablaEmpleado =
            """
                CREATE TABLE EMPLEADO (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                dni VARCHAR (10),
                nombre varchar (50),
                telefono INTEGER,
                fechaNac varchar (50),
                idEmpresa INTEGER)
                
            """.trimIndent()
        Log.i("base-datos", "Creando la tabla usuarios")
        db?.execSQL(scriptCrearTablaEmpresa)
        db?.execSQL(scriptCrearTablaEmpleado)

        val scriptQuemarDatosEmpresa=
            """INSERT INTO EMPRESA (ruc,razonSocial,direccion,telefono) 
            VALUES 	("1709492159001","Tourits Transport","Colinas del norte", 0999999901),
		            ("1725996365001","PC REPAIRS DP","El Condado", 0999999901),
                    ("1708394000001","Confecciones Enmita","Carcelen", 0999999901);""".trimIndent()
        db?.execSQL(scriptQuemarDatosEmpresa)

        val scriptQuemarDatosEmpleados=
            """INSERT INTO EMPLEADO (dni,nombre,telefono, fechaNac, idEmpresa) 
            VALUES 	("1709492159","Luis Proaño",0984951424, "12-07-1967", 1),
		            ("1725996365","Billy Proaño",0983333483, "01-10-1997", 2),
                    ("1708394000","Cecilia Morales",0987143826, "16-12-1965", 3);""".trimIndent()
        db?.execSQL(scriptQuemarDatosEmpleados)
    }

    //METODOS PARA LA ENTIDAD EMPRESA

    fun crearEmpresaFormulario(
        ruc: String,
        razonSocial: String,
        direccion: String,
        telefono: Int
    ): Boolean{

        val conexionExcritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("ruc", ruc)
        valoresAGuardar.put("razonSocial", razonSocial)
        valoresAGuardar.put("direccion", direccion)
        valoresAGuardar.put("telefono", telefono)
        val resultadoEscritura: Long = conexionExcritura
            .insert("EMPRESA",
                null,
                valoresAGuardar)

        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarEmpresas(): ArrayList<Empresa>{

        val scriptConsultarUsuario = "SELECT * FROM EMPRESA"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val listaEmpresas = arrayListOf<Empresa>()
        if(existeUsuario)
            do{
                val id = resultadoConsultaLectura.getInt(0)
                listaEmpresas.add(
                    Empresa(
                        id,
                        resultadoConsultaLectura.getString(1),
                        resultadoConsultaLectura.getString(2),
                        resultadoConsultaLectura.getString(3),
                        resultadoConsultaLectura.getInt(4)
                        )
                )
            } while ( resultadoConsultaLectura.moveToNext())

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaEmpresas
    }

    fun consultarEmpresaPorID(id: Int): Empresa {

        val scriptConsultarUsuario = "SELECT * FROM EMPRESA WHERE ID = ${id}"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val empresaEcontrado = Empresa(0,"", "", "", 0)
        if(existeUsuario)
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val ruc = resultadoConsultaLectura.getString(1)
                val razonSocial = resultadoConsultaLectura.getString(2)
                val direccion =
                    resultadoConsultaLectura.getString(3)
                val telefono = resultadoConsultaLectura.getInt(4)

                if (id!= null){
                    empresaEcontrado.id = id
                    empresaEcontrado.ruc = ruc
                    empresaEcontrado.razonSocial = razonSocial
                    empresaEcontrado.direccion = direccion
                    empresaEcontrado.telefono = telefono
                    //arregloUsuario.add(usuarioEncontrado)
                }
            } while ( resultadoConsultaLectura.moveToNext())

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return empresaEcontrado
    }

    fun eliminarEmpresa( id: Int): Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion  = conexionEscritura.delete(
            "EMPRESA",
            "id=?",
            arrayOf(
                id.toString()
            )
        )

        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() != -1){
          Log.i("bdd", "ELIMINAR EMPRESA ${id}")
          true
        } else {
            Log.i("bdd", "NO SE PUDO ELIMINAR ")
            false
        }
    }

    fun actualizarEmpresaFormulario(
        ruc: String,
        razonSocial: String,
        direccion: String,
        telefono: Int,
        id: Int
    ): Boolean{

        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("ruc", ruc )
        valoresActualizar.put("razonSocial", razonSocial )
        valoresActualizar.put("direccion", direccion)
        valoresActualizar.put("telefono", telefono)
        val resultadoActualizacion = conexionEscritura
            .update(
                "EMPRESA",
                valoresActualizar,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }

    //--------------------------METODOS PARA LA ENTIDAD EMPLEADO --------------------------------------
    fun crearEmpleadoFormulario(
        dni: String,
        nombre: String,
        fechaNac: String,
        telefono: Int,
        idEmpresa: Int
    ): Boolean{

        val conexionExcritura = writableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("dni", dni)
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("telefono", telefono)
        valoresAGuardar.put("fechaNac", fechaNac)
        valoresAGuardar.put("idEmpresa", idEmpresa)
        val resultadoEscritura: Long = conexionExcritura
            .insert("EMPLEADO",
                null,
                valoresAGuardar)

        conexionExcritura.close()
        return if (resultadoEscritura.toInt() == -1) false else true
    }

    fun consultarEmpleados(): ArrayList<Empleado> {

        val scriptConsultarUsuario = "SELECT * FROM EMPLEADO"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val listaEmpleados = arrayListOf<Empleado>()
        if(existeUsuario)
            do{
                val id = resultadoConsultaLectura.getInt(0)
                if (id!= null){
                   listaEmpleados.add(
                       Empleado(id,
                           resultadoConsultaLectura.getString(1),
                           resultadoConsultaLectura.getString(2),
                           resultadoConsultaLectura.getInt(3),
                           resultadoConsultaLectura.getString(4),
                           resultadoConsultaLectura.getInt(5)
                       )
                   )
                    //arregloUsuario.add(usuarioEncontrado)
                }
            } while ( resultadoConsultaLectura.moveToNext())

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaEmpleados
    }

    fun consultarEmpleadoPorIDEmpresa(id_empresa: Int): ArrayList<Empleado>{

        val scriptConsultarUsuario = "SELECT * FROM EMPLEADO WHERE idEmpresa = ${id_empresa}"
        val baseDatosLectura = readableDatabase
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarUsuario,
            null
        )
        val existeUsuario = resultadoConsultaLectura.moveToFirst()
        val listaEmpleados = arrayListOf<Empleado>()
        if(existeUsuario)
            do{
                val id = resultadoConsultaLectura.getInt(0)
                val dni = resultadoConsultaLectura.getString(1)
                val nombre = resultadoConsultaLectura.getString(2)
                val telefono = resultadoConsultaLectura.getInt(3)
                val fechaNac =
                    resultadoConsultaLectura.getString(4)
                val idEmpresa = resultadoConsultaLectura.getInt(5)

                if (id!= null){
                    listaEmpleados.add(
                        Empleado(
                            id,
                            dni,
                            nombre,
                            telefono,
                            fechaNac,
                            idEmpresa
                        )
                    )
                }
            } while ( resultadoConsultaLectura.moveToNext())

        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return listaEmpleados
    }


    fun eliminarEmpleadoPorIdEmpresa( id: Int): Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion  = conexionEscritura.delete(
            "EMPLEADO",
            "idEmpresa=?",
            arrayOf(
                id.toString()
            )
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun eliminarEmpleadoPorId( id: Int): Boolean{

        val conexionEscritura = writableDatabase
        val resultadoEliminacion  = conexionEscritura.delete(
            "EMPLEADO",
            "id=?",
            arrayOf(
                id.toString()
            )
        )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEmpleadoFormulario(
        dni: String,
        nombre: String,
        fechaNac: String,
        telefono: Int,
        id: Int
    ): Boolean{

        val conexionEscritura = writableDatabase
        val valoresActualizar = ContentValues()
        valoresActualizar.put("dni", dni )
        valoresActualizar.put("nombre", nombre )
        valoresActualizar.put("fechaNac", fechaNac)
        valoresActualizar.put("telefono", telefono)
        val resultadoActualizacion = conexionEscritura
            .update(
                "EMPLEADO",
                valoresActualizar,
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if(resultadoActualizacion == -1) false else true
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }
}

 */
