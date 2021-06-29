import java.util.*

fun main(args: Array<String>) {

    val arregloEmpresas = arrayListOf<Empresa>()

    arregloEmpresas.add(
        Empresa("1720540879001","PC REPAIRS DP",2476762, "13-07-2019",3000.00)
    )
    arregloEmpresas.add(
        Empresa("1725969503001","MEDICENTER ",3441319, "25-10-2020",50000.00)
    )
    println(arregloEmpresas [1])


    val arregloEmpleados = arrayListOf<Empleado>()

    arregloEmpleados.add(
        Empleado("1725996365","Billy Proaño",983333483,"01-10-1997", 450.50)
    )
    arregloEmpleados.add(
        Empleado("1708394000","Cecilia Morales",983333483,"16-12-1965", 652.00)
    )
    println(arregloEmpleados [0])
}

//PRIMERA ENTIDAD
class Empresa(
    val ruc: String,
    val razonSocial: String,
    val telefono: Int,
    val fechaInicio: String,
    val capital: Double,
){
    override fun toString(): String {
        return "Nombre: ${razonSocial} \n" +
                "RUC: ${ruc} \n" +
                "Telefono: ${telefono} \n" +
                "Fecha de apertura: ${fechaInicio} \n" +
                "Capital ${capital} \n"
    }
}

//SEGUNDA ENTIDAD
class Empleado(
    val dni: String,
    val nombre: String,
    val telefono: Int,
    val fechaNacimiento: String,
    val sueldo: Double,
){
    override fun toString(): String {
        return "Nombre: ${nombre} \n" +
                "Cedula de Ciudadania: ${dni} \n" +
                "Telefono: ${telefono} \n" +
                "Fecha de nacimiento: ${fechaNacimiento} \n" +
                "Sueldo ${sueldo} \n"
    }
}

