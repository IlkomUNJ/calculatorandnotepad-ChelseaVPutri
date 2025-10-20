package com.example.calculatormocom

sealed class CalculatorAction {
    data class Number(val number: Int): CalculatorAction()
    data class  Operation(val operation: CalculatorOperation): CalculatorAction()
    object Clear: CalculatorAction()
    object Delete: CalculatorAction()
    object Decimal: CalculatorAction()
    object Calculate: CalculatorAction()
    object ParenthesesOpen : CalculatorAction()
    object ParenthesesClose : CalculatorAction()
}