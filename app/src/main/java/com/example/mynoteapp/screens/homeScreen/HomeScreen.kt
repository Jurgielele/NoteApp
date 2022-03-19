package com.example.mynoteapp.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.mynoteapp.components.NoteCard
import com.example.mynoteapp.navigation.NoteScreens
import com.example.mynoteapp.screens.noteScreen.AddEditNoteViewModel
import com.example.mynoteapp.utils.UUIDConverter

@ExperimentalComposeUiApi
@Composable
fun HomeScreen(navController: NavHostController, addEditNoteViewModel: AddEditNoteViewModel = hiltViewModel()) {
    Surface(modifier = Modifier.background(Color.Gray)   ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    navController.navigate(NoteScreens.AddEditNoteScreen.name + "/1")
                }) {
                    Icon(Icons.Filled.AddCircle, "")
                }
            }, floatingActionButtonPosition = FabPosition.End
        ) {
            val notesList = addEditNoteViewModel.noteList.collectAsState().value
            LazyColumn{
                items(notesList){ note ->
                    NoteCard(note = note) {
                        val id = UUIDConverter().fromUUID(note.id)
                        navController.navigate(NoteScreens.AddEditNoteScreen.name+"/"+id)
                    }
                    
                }
            }
        }
    }
}

