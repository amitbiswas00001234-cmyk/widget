package com.example.sampleapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Stack

class MainActivity : AppCompatActivity() {
    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = 0.0
    private var isNewOp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { onDigitClick((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { onOperatorClick("+") }
        findViewById<Button>(R.id.btnSub).setOnClickListener { onOperatorClick("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { onOperatorClick("×") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { onOperatorClick("÷") }
        findViewById<Button>(R.id.btnMod).setOnClickListener { onOperatorClick("%") }

        findViewById<Button>(R.id.btnAC).setOnClickListener {
            currentInput = ""
            firstOperand = 0.0
            operator = ""
            isNewOp = true
            tvDisplay.text = "0"
        }

        findViewById<Button>(R.id.btnDel).setOnClickListener {
            if (currentInput.isNotEmpty()) {
                currentInput = currentInput.dropLast(1)
                tvDisplay.text = if (currentInput.isEmpty()) "0" else currentInput
            }
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener {
            if (operator.isNotEmpty() && currentInput.isNotEmpty()) {
                val secondOperand = currentInput.toDouble()
                val result = when (operator) {
                    "+" -> firstOperand + secondOperand
                    "-" -> firstOperand - secondOperand
                    "×" -> firstOperand * secondOperand
                    "÷" -> if (secondOperand != 0.0) firstOperand / secondOperand else Double.NaN
                    "%" -> firstOperand % secondOperand
                    else -> 0.0
                }
                tvDisplay.text = formatResult(result)
                currentInput = result.toString()
                operator = ""
                isNewOp = true
            }
        }
    }

    private fun onDigitClick(digit: String) {
        if (isNewOp) {
            currentInput = ""
            isNewOp = false
        }
        if (digit == "." && currentInput.contains(".")) return
        currentInput += digit
        tvDisplay.text = currentInput
    }

    private fun onOperatorClick(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput.toDouble()
            operator = op
            isNewOp = true
        }
    }

    private fun formatResult(result: Double): String {
        return if (result == result.toLong().toDouble()) {
            result.toLong().toString()
        } else {
            result.toString()
        }
    }
}