package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lab3.databinding.ActivityMainBinding
import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private var firstNumber = 0.0
    private var secondNumber = 0.0
    private var currentOperation = ""
    private var currentOperand1 = ""
    private var currentOperand2 = ""
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button0.setOnClickListener { numberPressed("0") }
        binding.button1.setOnClickListener { numberPressed("1") }
        binding.button2.setOnClickListener { numberPressed("2") }
        binding.button3.setOnClickListener { numberPressed("3") }
        binding.button4.setOnClickListener { numberPressed("4") }
        binding.button5.setOnClickListener { numberPressed("5") }
        binding.button6.setOnClickListener { numberPressed("6") }
        binding.button7.setOnClickListener { numberPressed("7") }
        binding.button8.setOnClickListener { numberPressed("8") }
        binding.button9.setOnClickListener { numberPressed("9") }
        binding.combutton.setOnClickListener { numberPressed(".") }
        binding.buttonPlus.setOnClickListener { operationPressed("+") }
        binding.buttonMinus.setOnClickListener { operationPressed("-") }
        binding.buttonMultiply.setOnClickListener { operationPressed("*") }
        binding.buttonDivide.setOnClickListener { operationPressed("/") }
        binding.PMbutton.setOnClickListener { changeSignPressed() }
        binding.buttonEquals.setOnClickListener { equalsPressed() }
        binding.buttonClear.setOnClickListener { clearPressed() }
        binding.Perbutton.setOnClickListener { percentPressed() }
    }

    private fun numberPressed(number: String) {
        if (currentOperation.isEmpty()) {
            if (number == "." && currentOperand1.contains(".")) return
            Log.d("firstNum",firstNumber.toString())
            currentOperand1 += number
            binding.resultTextView.text = currentOperand1
            firstNumber = currentOperand1.toDouble()

        } else {
            if(number == "." && currentOperand2.contains(".")) return
            Log.d("secondNum",secondNumber.toString())
            currentOperand2 += number
            binding.resultTextView.text = currentOperand2
            secondNumber = currentOperand2.toDouble()
        }
    }

    private fun operationPressed(operation: String) {
        currentOperation = operation
        binding.resultTextView.text = ""

    }
    private fun changeSign(number: Double): Double {
        return -number
    }
    private fun changeSignPressed() {
        var result = binding.resultTextView.text.toString().toDouble()
        var result2: Int
        if (result != 0.0){
            result = changeSign(result)
            if (result % 1 == 0.0) {
                result2 = result.toInt()
                binding.resultTextView.text = result2.toString()
                firstNumber = result2.toDouble()
            }
            else
            {
                binding.resultTextView.text = result.toString()
                firstNumber = result
            }
        }
    }
    private fun equalsPressed() {
        var result = 0.0
        when (currentOperation) {
            "" -> result = firstNumber
            "+" -> result = firstNumber + secondNumber
            "-" -> result = firstNumber - secondNumber
            "*" -> result = firstNumber * secondNumber
            "/" -> {
                if (secondNumber == 0.0) {
                    result = Double.NaN
                } else {
                    result = firstNumber / secondNumber
                }
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
        if (result.isNaN()) {
            binding.resultTextView.text = "Error"
        } else {
            if (result % 1 == 0.0) {
                var result2 = result.toInt()
                binding.resultTextView.text = result2.toString()
            }
            else {
                var fulresult = BigDecimal(result)
                binding.resultTextView.text = fulresult.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros().toString()
            }
        }
        firstNumber = result
        secondNumber = 0.0
        currentOperation = ""
        currentOperand1 = result.toString()
        currentOperand2 = ""
    }
    private fun percentPressed() {
        if (firstNumber != 0.0) {
            val percent = firstNumber / 100.0
            binding.resultTextView.text = percent.toString()
            firstNumber = percent
        }
    }

    private fun clearPressed() {
        binding.resultTextView.text = "0"
        firstNumber = 0.0
        secondNumber = 0.0
        currentOperation = ""
        currentOperand1 = ""
        currentOperand2 = ""
    }
}