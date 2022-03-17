package com.example.mynoteapp.screens.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.mynoteapp.components.NoteCard

@Composable
fun HomeScreen(navController: NavHostController) {
    Surface(modifier = Modifier.background(Color.Gray)   ) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    //todo click to navigate to add screen
                }) {
                    Icon(Icons.Filled.AddCircle, "")
                }
            }, floatingActionButtonPosition = FabPosition.End
        ) {
            Column() {
                NoteCard() {
                }
                NoteCard() {
                }
                NoteCard() {
                    //navigate
                }
            }
        }
    }
}

