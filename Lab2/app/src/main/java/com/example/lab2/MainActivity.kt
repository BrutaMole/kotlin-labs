package com.example.lab2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
   // val a =  binding.editA.text.toString().toDouble()
   // val b = binding.editB.text.toString().toDouble()
    //val c = binding.editC.text.toString().toDouble()
    fun sqr(a: Double) = a * a
    fun calculate(a: Double,b: Double,c: Double) = sqr(b) - 4 * a * c
    fun findAnswer(view: View) {
        val alert1 = AlertDialog.Builder(this).setPositiveButton("Понял", { d, id->d.cancel() } )
        try {
            if (binding.editA.text.toString().toDoubleOrNull() is Double || binding.editB.text.toString().toDoubleOrNull() is Double
                || binding.editC.text.toString().toDoubleOrNull() is Double) {
                val sqrt = kotlin.math.sqrt(calculate(binding.editA.text.toString().toDouble(), binding.editB.text.toString().toDouble(), binding.editC.text.toString().toDouble()))
                val x1 = (-binding.editB.text.toString().toDouble() + sqrt)/ (2 * binding.editA.text.toString().toDouble())
                val x2 = (-binding.editB.text.toString().toDouble() - sqrt)/ (2 * binding.editA.text.toString().toDouble())
                var answer = "x1 = $x1 \nx2 = $x2"
                if(x1.isNaN())
                    answer = "x1 = Нет корня \nx2 = $x2"
                if(x2.isNaN())
                    answer = "x1 = $x1 \nx2 = Нет корня"
                if(x1.isNaN() && x2.isNaN())
                    answer = "x1 = Нет корня \nx2 = Нет корня"
                alert1.setMessage(answer).create()
                alert1.show()

            }
        }
        catch(e: NumberFormatException) {
            alert1.setMessage("Неверный ввод данных").create()
            alert1.show()
        }

    }
}