package com.example.calculatormocom

sealed class CalculatorOperation(val button: String) {
    object Add: CalculatorOperation("+")
    object Subtract: CalculatorOperation("-")
    object Multiply: CalculatorOperation("×")
    object Divide: CalculatorOperation("÷")
    object SquareRoot: CalculatorOperation("√")
    object Sin: CalculatorOperation("sin")
    object Cos: CalculatorOperation("cos")
    object Tan: CalculatorOperation("tan")
}