package Control

import crearEmpleado
import javax.swing.JOptionPane

fun crearEmpleadoMenu() {

    val cedula = JOptionPane.showInputDialog("Ingrese la cedula del empleado")
    val nombre = JOptionPane.showInputDialog("Ingrese nombre del empleado")
    val telefono = JOptionPane.showInputDialog("Ingrese el telefono del empleado")
    val fechaNacimineto= JOptionPane.showInputDialog("Ingrese la fecha de naciemiento")
    val sueldo = JOptionPane.showInputDialog("Ingrese el sueldo del empleado")
    val activo = JOptionPane.showInputDialog("Ingrese 1 para activo 0 para no-activo")
    val nuevo = crearEmpleado(cedula, nombre, telefono.toInt(), fechaNacimineto, sueldo.toDouble(), activo.toBoolean())

    escribirEnArchivo(nuevo.toString())
}