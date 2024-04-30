package com.example.app_calculadora

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    // Declaramos Variables a Utilizar
    var operecionAc = ""
    var num1 = Double.NaN
    var num2 = Double.NaN
    lateinit var textAnterior: TextView
    lateinit var textResul: TextView
    val suma = "+"
    val resta = "-"
    val divicion = "/"
    val multiplicacion = "*"
    val porciento = "%"

    lateinit var formDecimal: DecimalFormat
    // Función para saber qué operación seleccionó el usuario
    fun selecOperador(b: View) {
        val boton = b as Button
        val operador = boton.text.toString().trim()
        if(textAnterior.text.isNotEmpty() || !num1.isNaN() ) {
            calcular()
            operecionAc = when (operador) {
                "x" -> multiplicacion
                "÷" -> divicion
                else -> operador
            }
            if (operecionAc == "%"){
                calcular()
                textResul.text = formDecimal.format(num1)
                textAnterior.text= " "
            }else {
                textResul.text = formDecimal.format(num1) + operador
                textAnterior.text = ""
            }
        }

    }
    // Función para calcular la operación dada por el usuario
    fun calcular() {

        if (!num1.isNaN()) {
            // Extraer el segundo número de la entrada del usuario
            if(textAnterior.text.isEmpty()){
                textAnterior.text=num2.toString()
            }
            num2 = textAnterior.text.toString().toDouble()
            textAnterior.text=""
            // Realizar la operación basada en el operador almacenado
            when (operecionAc) {
                suma -> num1 += num2
                resta -> num1 -= num2
                multiplicacion -> num1 *= num2
                divicion -> {
                    if (num2 != 0.0) {
                        num1 /= num2

                    } else {
                        // Manejar la división por cero
                        textResul.text="Error"
                    }
                }
                porciento -> num1 /= 100

            }

        } else {
            // Convertir el texto anterior a Double y asignarlo a num1
            num1 = textAnterior.text.toString().toDouble()

        }
    }
    // Función para seleccionar el número elegido por el usuario
    fun selecNum(b: View)
    {
        val boton = b as Button
        textAnterior.text = textAnterior.text.toString() + boton.text.toString()
    }


    //funcion del boton igual
    fun igual(b:View)
    {
        calcular()
        textResul.text=num1.toString()
        textAnterior.text=""
    }

    //funcion de los botones C Y AC que son borrar los datos
    fun eliminar(b: View)
    {
        //Variables
        val boton :Button = b as Button
        var datosActual:CharSequence

        if (boton.text.toString().trim() == "C" && textAnterior.text.isNotEmpty()) {
            datosActual = textAnterior.text as CharSequence
            textAnterior.text = datosActual.subSequence(0, datosActual.length - 1).takeIf { it.isNotEmpty() } ?: ""
        }else{

            num1 = Double.NaN
            num2 = Double.NaN
            textAnterior.text = ""
            textResul.text = ""
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Definimos cuántos decimales va tener los números a mostrar
        formDecimal = DecimalFormat("####.###############")

        // Referenciamos los TextView
        textAnterior=findViewById(R.id.textView2)
        textResul = findViewById(R.id.textView3)


    }
}





