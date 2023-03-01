package com.example.lab2

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.lab2.databinding.ActivityMainBinding
import kotlin.math.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun sqr(a: Double) = a * a
    fun calculate(a: Double,b: Double,c: Double) = sqr(b) - 4 * a * c
    fun printAns(x1: Double,x2: Double, A: Double, B: Double, C: Double): String {
        val ans1: String
        val ans2: String
        if(x1.isNaN())
            ans1 = "Нет корня"
        else
            ans1 = "$x1"
        if(x2.isNaN())
            ans2 = "Нет корня"
        else
            ans2 = "$x2"
        if (A == 0.0 && B == 0.0 && C == 0.0)
            return "0 = 0"
        else if (A == 0.0 && B == 0.0 && C != 0.0)
            return "$C != 0"
        else if (A == 0.0)
            return "Линейное уравнение"
        else if (x1 == x2)
            return "x = $x1"
        else
            return "x1 = $ans1 \nx2 = $ans2"
    }
    fun findAnswer(view: View) {
        try {
            if (binding.editA.text.toString().toDoubleOrNull() is Double || binding.editB.text.toString().toDoubleOrNull() is Double
                || binding.editC.text.toString().toDoubleOrNull() is Double) {
                val sqrt = sqrt(calculate(binding.editA.text.toString().toDouble(), binding.editB.text.toString().toDouble(), binding.editC.text.toString().toDouble()))
                var x1 = (-binding.editB.text.toString().toDouble() + sqrt)/ (2 * binding.editA.text.toString().toDouble())
                var x2 = (-binding.editB.text.toString().toDouble() - sqrt)/ (2 * binding.editA.text.toString().toDouble())
                if (!x1.isNaN())
                    x1 = (x1 * 100).roundToInt() / 100.0
                if(!x2.isNaN())
                    x2 = (x2 * 100).roundToInt() / 100.0
                binding.resultText.text = printAns(x1,x2,binding.editA.text.toString().toDouble(),binding.editB.text.toString().toDouble(),
                    binding.editC.text.toString().toDouble())
            }
        }
        catch(e: NumberFormatException) {
            val alert1 = AlertDialog.Builder(this).setPositiveButton("Понял", { d, id->d.cancel() } )
            alert1.setMessage("Ввод принимает только рациональные числа!").create()
            alert1.show()
        }
    }
}