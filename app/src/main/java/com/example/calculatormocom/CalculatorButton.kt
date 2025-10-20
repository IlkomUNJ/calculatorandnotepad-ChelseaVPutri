package com.example.calculatormocom

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(button: String, modifier: Modifier, onClick: () -> Unit) {
    ElevatedButton(
        modifier = if(button == "0") {
            Modifier.width(148.dp).height(70.dp)
        } else {
            Modifier.size(70.dp)
        },
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = if(button == "=") Color(0xFF4361ee) else Color(0xFFdee2e6),
            contentColor = Color.Black),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 1.dp, pressedElevation = 2.dp, disabledElevation = 0.dp),
        onClick = onClick
    ) {
        val scientificOperator = button in setOf<String>("sin", "cos", "tan", "AC")
        Text(text = button, style = TextStyle(fontSize = if(scientificOperator) 12.sp else 18.sp), color = getColor(button))
    }
}

fun getColor(button: String) : Color {
   val operators = setOf<String>("(", ")", "%", "+", "-", "×", "÷", "sin", "cos", "tan", "√", "log")

   if(button == "AC" || button == "⌦")
       return Color.Red
   if(button == "=")
       return Color.White
   if(button in operators)
       return Color.Blue
    return Color.Black
}

@Preview(showBackground =  true)
@Composable
fun CalculatorButtonPreview() {
//    CalculatorButton(
//        button = "AC",
//        modifier = Modifier
}