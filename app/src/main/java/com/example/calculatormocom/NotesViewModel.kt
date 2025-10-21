package com.example.calculatormocom

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

data class Note (
    val fileName: String,
    val content: String
)

class NotesViewModel(val context: Context) : ViewModel() {
    private val notesDir = File(context.filesDir, "notes")
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes = _notes.asStateFlow()

    fun loadNotes() {
        viewModelScope.launch {
            val filesDir = context.filesDir
            val noteFiles = notesDir.listFiles {
                file -> file.name.endsWith(".html")
            }?.sortedByDescending { it.lastModified() } ?: emptyList()
            _notes.value = noteFiles.map { file ->
                Note(
                    fileName = file.name,
                    content = file.readText()
                )
            }
        }
    }

    fun saveNote(fileName: String, content: String) {
        viewModelScope.launch {
            val file = File(notesDir, "$fileName.html")
            file.writeText(content)
            loadNotes()
        }
    }

    fun readNote(file: File) : String {
        return file.readText()
    }

    init {
        if (!notesDir.exists()) {
            notesDir.mkdirs()
        }
        loadNotes()
    }
}