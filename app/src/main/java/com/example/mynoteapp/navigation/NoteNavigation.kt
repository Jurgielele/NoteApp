package com.example.mynoteapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.screens.homeScreen.HomeScreen
import com.example.mynoteapp.screens.noteScreen.AddEditNoteScreen
import com.example.mynoteapp.screens.noteScreen.AddEditNoteViewModel
import java.util.*


@ExperimentalComposeUiApi
@Composable
fun NoteNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NoteScreens.HomeScreen.name){
        composable(NoteScreens.HomeScreen.name){
            val addEditNoteViewModel = hiltViewModel<AddEditNoteViewModel>()

            HomeScreen(navController = navController,
                addEditNoteViewModel = addEditNoteViewModel)
        }
        composable(NoteScreens.AddEditNoteScreen.name+"/{noteId}"){ it->
            val addEditNoteViewModel = hiltViewModel<AddEditNoteViewModel>()
            AddEditNoteScreen(navController = navController,
                addEditNoteViewModel = addEditNoteViewModel,
                noteId = it.arguments?.getString("noteId")!!)
        }
    }
}