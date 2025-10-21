package com.example.calculatormocom

import android.widget.ListView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.rememberRichTextState

@Composable
fun NotesListScreen(navController: NavController, viewModel: NotesViewModel) {
    val savedNotes by viewModel.notes.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
            ) {
                Text("Back")
            }

            LazyColumn(modifier = Modifier.padding(16.dp).background(Color.LightGray)) {
                items(savedNotes) { noteFile ->
                    Text(
                        text = noteFile.fileName,
                        modifier = Modifier.fillMaxWidth().padding(12.dp)
                    )

                    Text(
                        text = noteFile.content
                    )
                }
            }
        }

        FloatingActionButton(
            modifier = Modifier.align(Alignment.BottomEnd).padding(20.dp),
            onClick = { navController.navigate("text_editor") }
        ) {
            Icon(Icons.Default.Add, contentDescription = "New Note")
        }
    }
}