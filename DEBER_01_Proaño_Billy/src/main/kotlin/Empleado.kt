import javax.swing.JOptionPane

class Empleado (
    var dni: String,
    var nombre: String,
    var telefono: Int,
    var fechaNacimiento: String,
    var sueldo: Double,
    var activo: Boolean = true

    ) {

    override fun toString(): String {
        return  "Nombre: ${nombre} \n" +
                "Cedula de ciudadnia: ${dni} \n" +
                "telefono: ${telefono} \n" +
                "Fecha de nacimiento: ${fechaNacimiento} \n" +
                "Sueldo: ${sueldo} \n" +
                "Activo: ${activo}"
        }
    }

    fun crearEmpleado(
        dni: String,
        nombre: String,
        telefono: Int,
        fechaNacimiento: String,
        sueldo: Double,
        activo: Boolean = true

    ): MutableList<Empleado> {
        return mutableListOf(Empleado(dni, nombre, telefono, fechaNacimiento, sueldo, activo))
    }


    fun buscarEmpleados(

        parametro: String,
        datoConsulta: String,
        listaempleados: MutableList<Empleado>
    ): List<Empleado> {
        var empleadosEncontrados: List<Empleado> = emptyList()
        when(parametro){
            "dni" ->{
                empleadosEncontrados = listaempleados
                    .filter { empleado: Empleado ->
                        return@filter empleado.dni == datoConsulta
                    }
            }
            "nombre" -> {
                empleadosEncontrados = listaempleados
                    .filter { empleado: Empleado ->
                        return@filter empleado.nombre == datoConsulta
                    }
            }
            "telefono" -> {
                val telefono = datoConsulta.toInt()
                empleadosEncontrados = listaempleados
                    .filter { empleado: Empleado ->
                        return@filter empleado.telefono == telefono
                    }
            }
            "sueldo" -> {
                val sueldo = datoConsulta.toDouble()
                empleadosEncontrados = listaempleados
                    .filter { empleado: Empleado ->
                        return@filter empleado.sueldo == sueldo
                    }
            }
            "activo" -> {
                val activo = datoConsulta.toBoolean()
                empleadosEncontrados = listaempleados
                    .filter { empleado: Empleado ->
                        return@filter empleado.activo == activo
                    }
            }

            else -> {
                JOptionPane.showMessageDialog(null, "Información ${parametro} no encontrada ")
            }
        }
        return empleadosEncontrados
    }
