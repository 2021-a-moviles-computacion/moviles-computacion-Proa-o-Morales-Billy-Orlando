import `control-entidades`.*
import javax.swing.JOptionPane

fun main(args: Array<String>) {
    menu()
}


fun menu() {
    val menuInicio = stringMenuInicio()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicio)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearEmpresaControl()
            }
            2 -> {
                editarEmpresaControl()
            }
            3 -> {
                eliminarEmpresaControl()
            }
            4 -> {
                buscarEmpresaControl()
            }
            5 -> {
                mostrarTodo()
            }
            6 -> {
                menuEmpleados()
            }
            7 ->{
                JOptionPane.showMessageDialog(null, "GRACIAS POR USAR CMS VUELVA PRONTO")

            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion != 7)
}

fun menuEmpleados() {
    val menuInicioEstudiante = stringMenuInicioEstudiante()
    do {
        val entradaTexto = JOptionPane.showInputDialog(menuInicioEstudiante)
        val opcion = Integer.parseInt(entradaTexto)
        when (opcion) {
            1 -> {
                crearEmpleado()
            }
            2 -> {
                editarEmpleado()
            }
            3 -> {
                eliminarEstudianteMenu()
            }
            4 -> {
                buscarEstudiantePorAtributoMenu()
            }
            5 -> {
                menu()
            }
            6 -> {
                mostrarTodo()
            }
            0 -> {
                if( regresarMenuPrincipal() == true){
                    menu()
                } else {
                    JOptionPane.showMessageDialog(null, "Ha seleccionado la opcion no, siga administradndo empleados")
                }
            }
            else -> {
                JOptionPane.showMessageDialog(null, "Opcion seleccionada no existe")
            }
        }
    } while (opcion == 0)
}

fun regresarMenuPrincipal(): Boolean {
    val opciones = arrayOf("Si", "No")
    val respuesta = JOptionPane.showOptionDialog(null, "Esta seguro que desea regresar al menu principal ",
        "Regresar Menu Principal",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0])
    return respuesta == 0
}