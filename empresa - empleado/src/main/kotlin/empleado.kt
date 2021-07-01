import javax.swing.JOptionPane

class Empleado(
        var dni: String,
        var nombre: String,
        var telefono: String,
        var fechaNacimiento: String,
        var activo: Boolean = true
) {

}

fun buscarEmpleado(
    parametro: String,
    datoConsulta: String,
    listaEmpresas: MutableList<Empresa>
): List<List<Any>?> {
    var empresasEncontradas: List<List<Any>?> = emptyList()
    when (parametro) {
        "nombre" -> {
            empresasEncontradas = listaEmpresas
                    .map { empresa: Empresa ->
                        val empresaYempleado = empresa.empleados?.filter { empleado: Empleado ->
                            return@filter empleado.nombre == datoConsulta
                        }
                        return@map empresaYempleado?.let { listOf<Any>(empresa, it) }
                    }.filter { list: List<Any>? ->
                        return@filter list != null
                    }.filter { list ->
                        val empleadosFiltrados = list?.get(1) as List<Empleado>
                        return@filter empleadosFiltrados.size > 0
                    }
        }
        "dni" -> {
            empresasEncontradas = listaEmpresas
                    .map { empresa: Empresa ->
                        val empresaYempleado = empresa.empleados?.filter { empleado: Empleado ->
                            return@filter empleado.dni == datoConsulta
                        }
                        return@map empresaYempleado?.let { listOf<Any>(empresa, it) }
                    }.filter { list: List<Any>? ->
                        return@filter list != null
                    }.filter { list ->
                        val empleadosFiltrados = list?.get(1) as List<Empleado>
                        return@filter empleadosFiltrados.size > 0
                    }
        }
        else -> {
            JOptionPane.showMessageDialog(null, "Parametro ${parametro} no encontrado")
        }
    }
    return empresasEncontradas
}

fun editarEmpleado(
    dni: String,
    campoAEditar: String,
    nuevoValor: String,
    empresas: MutableList<Empresa>
): MutableList<Empresa> {
    val indices = buscarYRetornarIndices(dni, empresas)
    val existeEmpresa = indices["empresa"]!! > -1
    if (existeEmpresa) {
        val indiceEmpresa = indices["empresa"] as Int
        val indiceEmpleado = indices["empleado"] as Int
        when (campoAEditar) {
            "telefono" -> {
                empresas[indiceEmpresa].empleados?.get(indiceEmpleado)?.telefono = nuevoValor
            }
            "activo" -> {
                empresas[indiceEmpresa].empleados?.get(indiceEmpleado)?.activo = nuevoValor.toBoolean()
            }
        }
    }
    return empresas
}


fun crearEmpleado(

    nombreEmpresa: String,
    empleado: Empleado,
    empresas: MutableList<Empresa>
): MutableList<Empresa> {
    val indiceEmpresa = buscarYRetornarIndice(nombreEmpresa, empresas)
    val existeEmpresa = indiceEmpresa > -1
    if (existeEmpresa) {
        empresas[indiceEmpresa].empleados?.add(empleado)
    }
    return empresas
}


fun eliminarEmpleado(
    dni: String,
    empresas: MutableList<Empresa>
): MutableList<Empresa> {
    val indices = buscarYRetornarIndices(dni, empresas)
    val existeEmpresa = indices["empresa"]!! > -1
    val existeEmpleado = indices["empleado"]!! > -1

    if (existeEmpresa && existeEmpleado) {
        val indiceEmpresa = indices["empresa"] as Int
        val indiceEmpleado = indices["empleado"] as Int
        empresas[indiceEmpresa].empleados?.removeAt(indiceEmpleado)
    }
    return empresas
}

fun buscarYRetornarIndices(
    dni: String,
    empresas: MutableList<Empresa>
): Map<String, Int?> {
    val respuesta = empresas
            .map { empresa: Empresa ->
                val empresaYempleado = empresa.empleados?.filter { empleado: Empleado ->
                    return@filter empleado.dni == dni
                }
                return@map empresaYempleado?.let { listOf<Any>(empresa, it) }
            }.filter { list: List<Any>? ->
                return@filter list != null
            }.filter { list ->
                val empleadosFiltrados = list?.get(1) as List<Empleado>
                return@filter empleadosFiltrados.size > 0
            }

    val encontroRespuesta = respuesta.size > 0
    if (!encontroRespuesta) {
        JOptionPane.showMessageDialog(null, "No se encontro empleado con cedula: ${dni}")
        return mapOf<String, Int?>("empresa" to -1, "empleado" to -1)
    }
    val indiceEmpresa = empresas.indexOf(respuesta[0]?.get(0))
    val empleado = respuesta[0]?.get(1) as List<Empleado>
    val indiceEmpleado = empresas[indiceEmpresa].empleados?.indexOf(empleado[0])
    val indices = mapOf<String, Int?>("empresa" to indiceEmpresa, "empleado" to indiceEmpleado)
    return indices
}