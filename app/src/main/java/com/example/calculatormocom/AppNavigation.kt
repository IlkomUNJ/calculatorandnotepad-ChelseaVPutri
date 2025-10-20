package com.example.calculatormocom

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.*
import com.example.calculatormocom.ui.theme.Placeholder

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navController)
        }

        composable("calculator_screen") {
            CalculatorScreen(
                state = state,
                onAction = viewModel::onAction,
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }

        composable("text_editor") {
            TextEditor(navController)
        }
    }
}