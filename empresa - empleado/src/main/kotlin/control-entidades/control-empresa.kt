package `control-entidades`

import Empresa
import `control-archivos`.escribirEnArchivo
import `control-archivos`.leerArchivo
import buscarEmpresaControl
import crearEmpresaControl
import editarEmpresaControl
import eliminarEmpresaControl
import listarInformacion
import javax.swing.JOptionPane

fun stringMenuInicio(): String {
    return "     BIENVENIDO AL CMS (Company Managment System)      \n\n" +
            "MENU DE OPCIONES:\n \n" +
            "1. Crear Empresa\n" +
            "2. Actualizar Empresa\n" +
            "3. Eliminar Empresa\n" +
            "4. Buscar Empresa por atributo\n" +
            "5. Listar todas las Empresas\n" +
            "6. Gestionar empleados\n" +
            "7. Salir\n"
}

fun crearEmpresaControl() {

    val nombre = JOptionPane.showInputDialog("Ingrese la razon social")
    val ruc = JOptionPane.showInputDialog("Ingrese el ruc de la empresa")
    val telefono = JOptionPane.showInputDialog("Ingrese el telefono de la empresa")
    val fechaInicio = JOptionPane.showInputDialog("Ingrese la fecha de apertura de la empresa")
    val capital = JOptionPane.showInputDialog("Ingrese el capital de la empresa")
    val datos = leerArchivo()
    val empresaNueva = crearEmpresaControl(ruc,nombre,telefono.toInt(),fechaInicio,capital.toDouble())
    val nuevo = datos + empresaNueva
    escribirEnArchivo(nuevo)
}

fun editarEmpresaControl() {
    val nombre = JOptionPane.showInputDialog("Ingrese nombre de la empresa a editar")
    val campo = JOptionPane.showInputDialog("Ingrese campo de la empresa a editar")
    val nuevoValor = JOptionPane.showInputDialog("Ingrese el nuevo valor")
    val datos = leerArchivo()
    val empresaEditada = editarEmpresaControl(nombre, campo, nuevoValor, datos)
    escribirEnArchivo(empresaEditada)
}

fun eliminarEmpresaControl() {
    val nombre = JOptionPane.showInputDialog("Ingrese el nombre de la empresa a eliminar")
    val datos = leerArchivo()
    val empresaEditada = eliminarEmpresaControl(nombre, datos)
    escribirEnArchivo(empresaEditada)
}

fun buscarEmpresaControl() {
    val campo = JOptionPane.showInputDialog("Ingrese campo por el que desea buscar")
    val consulta = JOptionPane.showInputDialog("Ingrese su busqueda")
    val datos = leerArchivo()
    val empresaEncontrada = buscarEmpresaControl(campo, consulta, datos)
    val existe = empresaEncontrada.size > 0
    if (existe) {
        var menuRespuesta = ""
        empresaEncontrada.forEach { empresa: Empresa? ->
            menuRespuesta += "-------------------------------------------------\n" +
                    "Nombre: ${empresa?.razonSocial}\n" +
                    "Fundacion: ${empresa?.fechaInicio}\n" +
                    "Numero de empleados: ${empresa?.empleados?.size}\n"
        }
        menuRespuesta += "-------------------------------------------------\n"
        JOptionPane.showMessageDialog(null, menuRespuesta)
    } else {
        JOptionPane.showMessageDialog(null, "Empresa no encontrada")
    }
}

fun mostrarTodo() {
    val total = listarInformacion()
    JOptionPane.showMessageDialog(null, total)
}