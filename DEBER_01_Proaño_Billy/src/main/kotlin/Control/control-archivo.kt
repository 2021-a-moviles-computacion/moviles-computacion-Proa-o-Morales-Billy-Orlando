package Control

import java.io.File
import java.io.IOException
import java.io.InputStream


fun escribirEnArchivo(dato: Any) {

    val empleado: String = dato as String
    val fileName = "DEBER.txt"
    val fileExist = File(fileName)
    try {
        if(!fileExist.exists()){
            File(fileName).printWriter().use { out ->
                out.println(empleado)
            }

        }else{
            fileExist.appendText(empleado)
        }
    } catch (e: IOException) {
        print(e)
    }
}

fun leerArchivo(){
    val inputStream: InputStream = File("DEBER.txt").inputStream()

    val inputString = inputStream.bufferedReader().use { it.readText() }
    println(inputString)

}
