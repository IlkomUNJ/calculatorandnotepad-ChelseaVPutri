package com.example.calculatormocom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier,
    state: CalculatorState,
    onAction: (CalculatorAction) -> Unit,
    navController: NavController
) {

    Box(modifier = modifier) {
        Column(
           modifier = Modifier.align(Alignment.TopStart),
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp)
            ) {
                Text("Back")
            }
        }

        Column(
            modifier = modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.Bottom
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().weight(1f),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
//                text = "12 + 12",
                    text = state.equation,
                    style = TextStyle(fontSize = 30.sp, textAlign = TextAlign.End),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = state.result,
                    style = TextStyle(fontSize = 60.sp, textAlign = TextAlign.End, color = Color.Gray),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(16.dp))
            }


            Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton(
                        button = "AC",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Clear)}
                    )

                    CalculatorButton(
                        button = "(",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { onAction(CalculatorAction.ParenthesesOpen) }
                    )

                    CalculatorButton(
                        button = ")",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = { onAction(CalculatorAction.ParenthesesClose) }
                    )

                    CalculatorButton(
                        button = "⌦",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Delete)}
                    )

                    CalculatorButton(
                        button = "=",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Calculate)}
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton(
                        button = "7",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(7))}
                    )

                    CalculatorButton(
                        button = "8",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(8))}
                    )

                    CalculatorButton(
                        button = "9",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(9))}
                    )

                    CalculatorButton(
                        button = "÷",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Divide))}
                    )

                    CalculatorButton(
                        button = "sin",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Sin))}
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton(
                        button = "4",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(4))}
                    )

                    CalculatorButton(
                        button = "5",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(5))}
                    )

                    CalculatorButton(
                        button = "6",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(6))}
                    )

                    CalculatorButton(
                        button = "×",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))}
                    )

                    CalculatorButton(
                        button = "cos",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Cos))}
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton(
                        button = "1",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(1))}
                    )

                    CalculatorButton(
                        button = "2",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(2))}
                    )

                    CalculatorButton(
                        button = "3",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Number(3))}
                    )

                    CalculatorButton(
                        button = "-",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))}
                    )

                    CalculatorButton(
                        button = "tan",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Tan))}
                    )
                }

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    CalculatorButton(
                        button = "0",
                        modifier = Modifier.weight(2f).aspectRatio(2f),
                        onClick = {onAction(CalculatorAction.Number(0))}
                    )

                    CalculatorButton(
                        button = ".",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Decimal)}
                    )

                    CalculatorButton(
                        button = "+",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.Add))}
                    )

                    CalculatorButton(
                        button = "√",
                        modifier = Modifier.weight(1f).aspectRatio(1f),
                        onClick = {onAction(CalculatorAction.Operation(CalculatorOperation.SquareRoot))}
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview() {
//    CalculatorScreen
}