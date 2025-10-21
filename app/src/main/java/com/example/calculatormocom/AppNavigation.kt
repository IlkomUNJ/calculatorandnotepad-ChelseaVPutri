package com.example.calculatormocom

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.compose.*
import com.example.calculatormocom.ui.theme.Placeholder

@Composable
fun AppNavigation(appContext: Context) {
    val navController = rememberNavController()

    val calculatorViewModel = viewModel<CalculatorViewModel>()
    val calculatorState = calculatorViewModel.state

    val notesViewModel: NotesViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun<T: ViewModel> create(modelClass: Class<T>) : T {
                return NotesViewModel(appContext) as T
            }
        }
    )

    NavHost(navController = navController, startDestination = "home_screen") {
        composable("home_screen") {
            HomeScreen(navController)
        }

        composable("calculator_screen") {
            CalculatorScreen(
                state = calculatorState,
                onAction = calculatorViewModel::onAction,
                modifier = Modifier.fillMaxSize(),
                navController = navController
            )
        }

        composable("notes_list") {
//            TextEditor(navController)
            NotesListScreen(navController, notesViewModel)
        }

        composable("text_editor") { 
            TextEditor(navController, notesViewModel)
        }
    }
}