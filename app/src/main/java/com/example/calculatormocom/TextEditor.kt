package com.example.calculatormocom

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
//import androidx.compose.material3.ExposedDropdownMenuBoxScope.menuAnchor
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.mohamedrejeb.richeditor.model.RichTextState
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import java.io.File

@Composable
fun TextEditor(navController: NavController, viewModel: NotesViewModel) {
    val state = rememberRichTextState()
    var fileName by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        val isBold = state.currentSpanStyle.fontWeight == FontWeight.Bold
        val isItalic = state.currentSpanStyle.fontStyle == FontStyle.Italic
        val isUnderlined = state.currentSpanStyle.textDecoration == TextDecoration.Underline

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.padding(vertical = 5.dp)
        ) {
            Text("Back")
        }

        OutlinedTextField(
            value = fileName,
            onValueChange = { fileName = it },
            label = { Text("File name") },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )

        Row(
            modifier = Modifier.padding(bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Font size",
                maxLines = 2,
                fontSize = 16.sp
            )
            DropdownMenu(richTextState = state)
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        ) {
            StyleButton(
                button = "Bold",
                isToggled = isBold,
                onClick = { state.toggleSpanStyle(SpanStyle(fontWeight = FontWeight.Bold)) }
            )

            StyleButton(
                button = "Italic",
                isToggled = isItalic,
                onClick = { state.toggleSpanStyle(SpanStyle(fontStyle = FontStyle.Italic)) }
            )

            StyleButton(
                button = "Underline",
                isToggled = isUnderlined,
                onClick = { state.toggleSpanStyle(SpanStyle(textDecoration = TextDecoration.Underline)) }
            )
        }

        RichTextEditor(
            state = state,
            modifier = Modifier.padding(horizontal = 12.dp).fillMaxWidth().height(250.dp).padding(vertical = 10.dp)
        )

        Button(
            onClick = {
                if(fileName.isNotBlank()) {
                    val contentToSave = state.toHtml()
                    viewModel.saveNote(fileName, contentToSave)
                    navController.popBackStack()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(richTextState: RichTextState) {
    val fontSizeList = listOf<Int>(12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24)
    var isExpanded by remember { mutableStateOf(false) }
    var selectedFontSize by remember { mutableIntStateOf(fontSizeList[0]) }

    Column(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ExposedDropdownMenuBox(
            expanded =isExpanded,
            onExpandedChange = { isExpanded = !isExpanded }
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                value = selectedFontSize.toString(),
                onValueChange = {},
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) }
            )

            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                fontSizeList.forEach { size ->
                    DropdownMenuItem(
                        text = { Text(size.toString()) },
                        onClick = {
                            selectedFontSize = size
                            richTextState.toggleSpanStyle(SpanStyle(fontSize = size.sp))
                            isExpanded = false
                        },
//                        onClick = { selectedFontSize = fontSizeList[index]; isExpanded = false },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
}


@Composable
fun StyleButton(button: String, isToggled: Boolean, onClick: () -> Unit) {
    OutlinedButton(
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(horizontal = 5.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isToggled) {

                MaterialTheme.colorScheme.primary
            } else {
                Color.White
            },
            contentColor = if (isToggled) {
                Color.White
            } else {
                MaterialTheme.colorScheme.primary
            }
        )
    ) {
        Text(
            text = button,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}