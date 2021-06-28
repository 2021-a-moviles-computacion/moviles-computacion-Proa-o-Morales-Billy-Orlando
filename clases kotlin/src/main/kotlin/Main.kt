fun main() {
    println("HOLA MUNDOI KT")

    var edadProfesor = 28

    //duck timping

    //tipo de variables

    //variables mutables

    //variables inmutables

    val cedulaIdentidad = 1725996365

    //cedulaIdentidad = 21231

    //preferible variables inmutables, para el desarrollo de aplicaciones
    // al menos que sea necesario cambiar el valor de alguna variable


    //SWITCH -> CASE

    val estadoCivil: String = "C"

    when (estadoCivil) {
        ("C") -> {
            println("Peligro alejese ")
        }

        ("S") -> {
            println("Soltero puede acercarse ")
        }

        else -> println("identificando estado civil")
    }

    //if de una sola linea, en kotlin

    val coqueteo = if (estadoCivil == "S") "SI" else "NO"

    //llamada de la funcion
    imprimirNombre("Billy")
    calcularSueldo(450.00, 30.00)

    //parametros nombrados para funcione
    calcularSueldo(
            bonoEspecial = 45.00,
            sueldo = 500.00
    )

    //ARRGLOS EN KOTLIN

    //arreglos estaticos
    val arreglosEstatico: Array<Int> = arrayOf(1,2,3)

    //arreglos dinamicos

    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)

    arregloDinamico.add(11)
    arregloDinamico.add(12)

    println(arregloDinamico)


    //OPERADORES DE LOS ARREGLOS, SIRVEN PARA LOS ESTATICOS Y DINAMICOS

    //FOR EACH -> Unit o vacio
    //nos ayuda a iterar un arreglo

    val respuestaForEach:  Unit =  arregloDinamico
            .forEach{ valorActual: Int ->
                println("Valor actual: ${valorActual}")
            }

    println(respuestaForEach)

    //OPERADOR MAP  NOS PERMITE MUTAR EL ARREGLO QUE YA TENGAMOS CREADO

    //1) enviamos el valor del arreglo
    //2) Nos devuelve un nuevo arreglo con los valores modificados

    val respuestaMap: List<Double> = arregloDinamico
            .map { valorActual: Int ->
                return@map valorActual.toDouble() + 100
            }

    println(respuestaMap)


    //OPERADOR FILTER
    //1) Devolver una expresion (TRUE o FALSE)
    //2) Nuevo arreglo filtrado

    val respuestaFilter: List<Int> = arregloDinamico
            .filter { valorActual: Int ->
                val mayoresACinco: Boolean = valorActual > 5
                return@filter mayoresACinco
            }
    println(respuestaFilter)


    val respuestaFilterDos = arregloDinamico.filter { it < 5 }
    println(respuestaFilterDos)


    //operadores AND y OR

    val respuestaAny: Boolean = arregloDinamico
        .any{ valorActual: Int ->
            return@any (valorActual > 5)
        }
    println(respuestaAny)

    val respuestaAll: Boolean = arregloDinamico
        .all { valorActual: Int ->
            return@all (valorActual > 5)
        }
    println(respuestaAll)


    //OPERADOR REDUCE nos permite devolver el valor acumulado

    val respuestaReduce: Int = arregloDinamico
        .reduce { acumulado: Int, valorActual:Int ->
            return@reduce acumulado + valorActual
        }
    println(respuestaReduce)

    // OPERADOR FOLD NOS PERMITE INICIAR CON UN VALOR DIFERENTE A CERO, OPERADOR UNICO DE KOTLIN

    val arregloDanio = arrayListOf<Int>(12,15,8, 10)

    val respuestaReduceFold = arregloDanio
        .fold( 100,
            {
                acumulado, valorActual ->
                return@fold acumulado - valorActual
            }
        )
    println(respuestaReduceFold)


    //USO DE VARIOS OPERADORES DENTRO DE UN SOLA VARIABLE

    val vidaActual: Double = arregloDinamico
        .map { it * 2.3}
        .filter { it > 20 }
        .fold( 100.00, { acc, i -> acc -i})
        .also { println(it) }


    println("valor de vida actual ${vidaActual}")


    val ejemploUno = Suma(1,2)
    val ejemploDos = Suma( null, 4)
    val ejemploTres = Suma ( 1, null)

    println(ejemploUno.sumar())
    println(Suma.historialSumas)
    println(ejemploDos.sumar())
    println(Suma.historialSumas)
    println(ejemploTres)
    println(Suma.historialSumas)



} // fin de función main

// Tema de funciones
fun imprimirNombre(nombre: String): Unit {
    println("Nombre:   ${nombre} ")
}

fun calcularSueldo(
        sueldo: Double,  //Requerido
        tasa: Double = 12.00,  //Opcional
        bonoEspecial: Double? = null // el simpbolo de interrogacion indica que la variable puede recibir un parametro nulo
): Double {

    if (bonoEspecial != null) {
        return sueldo * (100 / tasa) + bonoEspecial
    } else {
        return sueldo * (100 / tasa)
    }

}

// CLASES Y CLASES ABSTRACTAS

abstract  class  NumerosJava {
    protected val numeroUno: Int
    private  val numeroDos: Int
    constructor(
        uno: Int,
        dos: Int
    ){
        numeroUno = uno
        numeroDos = dos
        println("inicializar las variables ")
    }




}

// clase abstacta en kotlin

abstract  class Numeros( //constructor primario
   protected  var numeroUno: Int,
   protected  var numeroDos: Int
){
    //bloque init este bloque nos permite realizar inicializaciones
    //
    init {
        //bloque de inicio del contructor primario
        println("Inicializar")
    }
}

//uso de herencia
class Suma(
    uno: Int, //parametro requerido
    dos: Int, //parametro requerido
): Numeros(uno, dos){
    init {
        this.numeroUno
        this.numeroDos
    }

    constructor( //segundo constructor
        uno: Int?,
        dos: Int
    ): this(
        if(uno == null) 0 else uno,
        dos
    )

    constructor( //tercer constructor
        uno: Int,
        dos: Int?
    ): this(
        uno,
        if(dos == null) 0 else dos,
    )

    public  fun sumar(): Int{
        // val total: Int = this.numeroUno + this.numeroDos
        val total: Int = numeroUno + numeroDos //en kotlin no es necesario el uso de la palabra reservada this, ya que el
        // lenguaje entiende que se esta trabajndo dentro de una clase


        agregarHistrial(total)
        return  total
    }

    //USO DE SINGLETON

    companion object {
        val historialSumas = arrayListOf<Int>()

        fun agregarHistrial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
            println(historialSumas)
        }
    }

}