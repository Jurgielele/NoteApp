package com.example.mynoteapp.screens.noteScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mynoteapp.components.NoteInputText
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.navigation.NoteScreens
import java.util.*

@ExperimentalComposeUiApi
@Composable
fun AddEditNoteScreen(
    navController: NavHostController,
    noteId: String,
    addEditNoteViewModel: AddEditNoteViewModel = hiltViewModel()
) {
    if(noteId != "1"){
        addEditNoteViewModel.getNoteById(UUID.fromString(noteId))
    }
    Scaffold(
        floatingActionButton = {
            Row {
                Icon(
                    Icons.Filled.Delete,
                    "",
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            if(noteId.length>1){
                                addEditNoteViewModel.removeNote(Note(
                                    id = UUID.fromString(noteId),
                                    title = addEditNoteViewModel.getTitle(),
                                    addEditNoteViewModel.getDescription()
                                ))
                                navController.navigate(NoteScreens.HomeScreen.name)
                            }
                        }
                )
                Icon(
                    Icons.Filled.Check,
                    "",
                    modifier = Modifier
                        .size(56.dp)
                        .clickable {
                            if (noteId.length == 1) {
                                addEditNoteViewModel.addNote(Note(
                                    title = addEditNoteViewModel.getTitle(),
                                    description = addEditNoteViewModel.getDescription()
                                ))
                            }else{
                                addEditNoteViewModel.updateNote(UUID.fromString(noteId))
                            }
                            navController.navigate(NoteScreens.HomeScreen.name)
                        }
                )
            }
        }, floatingActionButtonPosition = FabPosition.End
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(AbsoluteCutCornerShape(topRight = 30f)),
            backgroundColor = Color(0xfffeff9c)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(4.dp)
            ) {

                NoteInputText(
                    text = addEditNoteViewModel.getTitle(),
                    onTextChange = addEditNoteViewModel::onTitleChange,
                    modifier = Modifier.fillMaxWidth(),
                    label = "Title"
                )
                Divider(modifier = Modifier.height(2.dp))
                Spacer(modifier = Modifier.height(4.dp))
                NoteInputText(
                    text = addEditNoteViewModel.getDescription(),
                    label = "Description",
                    onTextChange = addEditNoteViewModel::onDescriptionChange,
                    maxLine = 100,
                    modifier = Modifier
                        .fillMaxHeight(),
                )

            }
        }
    }
}


