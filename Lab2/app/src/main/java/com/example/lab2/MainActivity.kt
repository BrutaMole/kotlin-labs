package com.example.lab2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab2.databinding.ActivityMainBinding
import java.math.RoundingMode
import kotlin.math.*
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun sqr(a: Double) = a * a
    fun calculate(a: Double,b: Double,c: Double) = sqr(b) - 4 * a * c
    fun findAnswer(view: View) {
        try {

            if (binding.editA.text.toString().toDouble() == 0.0 && binding.editB.text.toString().toDouble() == 0.0)
            {
                val alert2 = AlertDialog.Builder(this).setPositiveButton("Понял", { d, id->d.cancel() } )
                alert2.setMessage("Не является уравнением!").create()
                alert2.show()
            }
            else if (binding.editA.text.toString().toDouble() == 0.0)
            {
                var x = -binding.editC.text.toString().toDouble() / binding.editB.text.toString().toDouble()
                binding.resultText.text = "x = $x"
            }
            else if (binding.editA.text.toString().toDoubleOrNull() is Double || binding.editB.text.toString().toDoubleOrNull() is Double
                || binding.editC.text.toString().toDoubleOrNull() is Double) {
                val disc = calculate(binding.editA.text.toString().toDouble(), binding.editB.text.toString().toDouble(), binding.editC.text.toString().toDouble())
                if (disc < 0.0)
                    binding.resultText.text = "Корней нет!"
                else if (disc == 0.0) {
                    var x = -binding.editB.text.toString()
                        .toDouble() / (2 * binding.editA.text.toString().toDouble())
                    binding.resultText.text = "x = $x"
                }
                else {
                    var x1 = ((-binding.editB.text.toString()
                        .toDouble() + sqrt(disc)) / (2 * binding.editA.text.toString().toDouble()))
                    var x2 = (-binding.editB.text.toString()
                        .toDouble() - sqrt(disc)) / (2 * binding.editA.text.toString().toDouble())
                    val df = DecimalFormat("#.##")
                    df.roundingMode = RoundingMode.DOWN
                    x1 = df.format(x1).toDouble()
                    x2 = df.format(x2).toDouble()
                    binding.resultText.text = "x1 = $x1\nx2 = $x2"
                }
            }
        }
        catch(e: NumberFormatException) {
            val alert1 = AlertDialog.Builder(this).setPositiveButton("Понял", { d, id->d.cancel() } )
            alert1.setMessage("Ввод принимает только рациональные числа!").create()
            alert1.show()
        }
    }
}