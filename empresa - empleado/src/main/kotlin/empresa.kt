import java.io.File
import java.io.InputStream
import javax.swing.JOptionPane

class Empresa(
    var ruc: String,
    var razonSocial: String,
    var telefono: Int,
    var fechaInicio: String,
    var capital: Double,
    var empleados: MutableList<Empleado>? = null
) {
}

fun buscarEmpresaControl(
    parametro: String,
    datoConsulta: String,
    empresas: MutableList<Empresa>
): List<Empresa> {
    var empresasEncontradas: List<Empresa> = emptyList()
    when (parametro) {
        "razonSocial" -> {
            empresasEncontradas = empresas
                .filter { empresa: Empresa ->
                    return@filter empresa.razonSocial == datoConsulta
                }
        }
        "ruc" -> {
            empresasEncontradas = empresas
                .filter { empresa: Empresa ->
                    return@filter empresa.ruc == datoConsulta
                }
        }
        else -> {
            JOptionPane.showMessageDialog(null,"Parametro de busqueda  ${parametro} no encontrado")
//            println("Campo ${campo} no encontrado")
        }
    }
    return empresasEncontradas
}

fun editarEmpresaControl(
    razonSocial: String,
    datoEditar: String,
    nuevoValor: String,
    empresas: MutableList<Empresa>
): MutableList<Empresa> {
    val indice = buscarYRetornarIndice(razonSocial, empresas)
    val existeEmpresa = indice > -1
    if (existeEmpresa) {
        when (datoEditar) {
            "razonSocial" -> {
                empresas[indice].razonSocial = nuevoValor
            }
            "ruc" -> {
                empresas[indice].ruc = nuevoValor
            }
            else -> {
                JOptionPane.showMessageDialog(null, "No se encontro la empresa  ${datoEditar}")
            }
        }
    }
    return empresas
}

fun crearEmpresaControl(
    ruc: String,
    razonSocial: String,
    telefono: Int,
    fechaInicio: String,
    capital: Double,
): MutableList<Empresa> {
    val empleados = mutableListOf<Empleado>()
    return mutableListOf(Empresa(ruc, razonSocial,telefono, fechaInicio,capital, empleados = empleados))
}

fun eliminarEmpresaControl(
    razonSocial: String,
    empresas: MutableList<Empresa>
): MutableList<Empresa> {
    val indice = buscarYRetornarIndice(razonSocial, empresas)
    val existeEmpresa = indice > -1
    if (existeEmpresa) {
        empresas.removeAt(indice)
    }
    return empresas
}

fun buscarYRetornarIndice(razonSocial: String, empresas: MutableList<Empresa>): Int {
    val respuesta = empresas.filter { empresa: Empresa ->
        return@filter empresa.razonSocial == razonSocial
    }
    val existeEmpresa = respuesta.size > 0
    if (!existeEmpresa) {
        JOptionPane.showMessageDialog(null, "No se encontro la empresa  ${razonSocial}")
        return -1
    }
    return empresas.indexOf(respuesta[0])

}

fun listarInformacion(): String {
    val inputStream: InputStream = File("DEBER.txt").inputStream()
    val inputString = inputStream.bufferedReader().use { it.readText() }
    return inputString
}
