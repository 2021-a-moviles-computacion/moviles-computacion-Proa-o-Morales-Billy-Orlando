package `control-entidades`

import Empleado
import Empresa
import `control-archivos`.escribirEnArchivo
import `control-archivos`.leerArchivo
import buscarEmpleado
import crearEmpleado
import editarEmpleado
import eliminarEmpleado
import javax.swing.JOptionPane


fun stringMenuInicioEstudiante(): String {
    return "      GESTION DE EMPLEADOS      \n\n" +
            "Seleccione las opciones:\n" +
            "1. Crear empleado\n" +
            "2. Actualizar empleado\n" +
            "3. Eliminar empleado\n" +
            "4. Buscar empleadp por atributo\n" +
            "5. Volver a GESTION DE EMPRESAS\n" +
            "6. Mostrar datos\n" +
            "0. Volver a menu Empresas\n"
}

fun crearEmpleado() {
    val datos = leerArchivo()
    val nombreEmpresa = JOptionPane.showInputDialog("Ingrese el nombre de la empresa donde crear el empleado")
    val dni = JOptionPane.showInputDialog("Ingrese el nunmero de cedula")
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre del empleado")
    val telefono = JOptionPane.showInputDialog("Ingrese el telefono del empleado")
    val fechaNacimiento = JOptionPane.showInputDialog("Ingrese la fecha de nacimiento")
    val estado = estadoTrabajo()
    val empleado = Empleado(dni,nombre, telefono,fechaNacimiento, estado)
    val empleadoCreado = crearEmpleado(nombreEmpresa, empleado, datos)
    escribirEnArchivo(empleadoCreado)
}

fun editarEmpleado() {
    val datos = leerArchivo()
    val nombre = JOptionPane.showInputDialog("Ingrese el numero de cedula del empleado a editar")
    val campo = JOptionPane.showInputDialog("Ingrese el campo del empleado a editar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor del empleado a editar")
    val respuesta = editarEmpleado(nombre, campo, nuevoValor, datos)
    escribirEnArchivo(respuesta)
}

fun eliminarEstudianteMenu() {
    val datos = leerArchivo()
    val nombre = JOptionPane.showInputDialog("Ingrese el numero de cedula del empleado a eliminar")
    val respuesta = eliminarEmpleado(nombre, datos)
    escribirEnArchivo(respuesta)
}

fun buscarEstudiantePorAtributoMenu() {
    val datos = leerArchivo()
    val campo = JOptionPane.showInputDialog("Ingrese el parametro por el que desea buscar")
    val consulta = JOptionPane.showInputDialog("Ingrese el dato a buscar")
    val respuesta = buscarEmpleado(campo, consulta, datos)
    var stringRespuesta = ""
    respuesta.forEach { list ->
        val empresa: Empresa = list?.get(0) as Empresa
        val arregloEmpleados: List<Empleado> = list?.get(1) as List<Empleado>
        val nombreEmpleados = arregloEmpleados.map { empleado: Empleado ->
            val arregloDatos = mutableMapOf<String, Any>();
            arregloDatos.put("dni", empleado.dni)
            arregloDatos.put("Nombre", empleado.nombre)
            arregloDatos.put("telefono", empleado.telefono)
            arregloDatos.put("Fecha Nacimiento", empleado.fechaNacimiento)
            arregloDatos.put("Estado", empleado.activo)
            return@map arregloDatos
        }
        stringRespuesta += "-----------------------------------------------------------------------------------------\n" +
                "Empresa: ${empresa.razonSocial}\n" +
                "Empleados: ${nombreEmpleados}\n"
    }
    stringRespuesta += "-----------------------------------------------------------------------------------------\n"
    JOptionPane.showMessageDialog(null, stringRespuesta)
}



fun estadoTrabajo(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "El empleado esta activo en la empresa ",
            "Esato Activo",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}
