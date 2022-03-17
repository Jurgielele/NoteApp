package com.example.mynoteapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mynoteapp.screens.homeScreen.HomeScreen

@ExperimentalComposeUiApi
@Composable
fun NoteNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NoteScreens.HomeScreen.name){
        composable(NoteScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(NoteScreens.AddEditNoteScreen.name){
            //AddEditNoteScreen(navController = navController)
        }
    }
}