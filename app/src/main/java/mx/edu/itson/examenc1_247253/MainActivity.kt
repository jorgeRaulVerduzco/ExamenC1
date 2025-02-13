package mx.edu.itson.examenc1_247253

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
     var contadorProducto = 0
     var subtotal = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.NProducto)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val cantidad: EditText = findViewById(R.id.txtCantidad)
        val producto: EditText = findViewById(R.id.txtProducto)
        val precio: EditText = findViewById(R.id.txtPrecio)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)


        btnAgregar.setOnClickListener {
            btnAgregar.setOnClickListener {
                if (contadorProducto < 3) {
                    val cantidadValue = cantidad.text.toString().toInt()
                    val productoValue = producto.text.toString()
                    val precioValue = precio.text.toString().toDouble()

                    contadorProducto++
                    val totalPrice = calculoPrecioPorCantidad(cantidadValue, precioValue)
                    subtotal += totalPrice

                    when (contadorProducto) {
                        1 -> {
                            findViewById<TextView>(R.id.txtVNumerDelProducto1).text = "1"
                            findViewById<TextView>(R.id.txtVNombreProducto1).text = productoValue
                            findViewById<TextView>(R.id.txtVPrecioProducto1).text = "$" + totalPrice.toString()
                        }
                        2 -> {
                            findViewById<TextView>(R.id.txtVNumerDelProducto2).text = "2"
                            findViewById<TextView>(R.id.txtVNombreProducto2).text = productoValue
                            findViewById<TextView>(R.id.txtVPrecioProducto2).text = "$" + totalPrice.toString()
                        }
                        3 -> {
                            findViewById<TextView>(R.id.txtVNumerDelProducto3).text = "3"
                            findViewById<TextView>(R.id.txtVNombreProducto3).text = productoValue
                            findViewById<TextView>(R.id.txtVPrecioProducto3).text = "$" + totalPrice.toString()
                        }
                    }

                    cantidad.setText("")
                    producto.setText("")
                    precio.setText("")
                }
            }

            btnCalcular.setOnClickListener {
                val iva = calcularIVA(0.16, subtotal)
                val total = subtotal + iva
                findViewById<TextView>(R.id.txtVCalculoSubtotal).text = "$" + subtotal.toString()
                findViewById<TextView>(R.id.txtVCalculoIVA).text = "$" + iva.toString()
                findViewById<TextView>(R.id.txtVCalculoTotal).text = "$" + total.toString()
            }
        }
    }


    fun calculoPrecioPorCantidad(cantidad: Int, precio: Double): Double {
        return cantidad * precio
    }

    fun calcularIVA(iva: Double, subtotal: Double): Double {
        return subtotal * 0.16
    }
}