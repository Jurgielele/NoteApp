package com.example.mynoteapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mynoteapp.model.Note

@Composable
fun NoteCard(note: Note, onClick: () -> Unit){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(AbsoluteCutCornerShape(topRight = 30f))
            .clickable {
                onClick()
            },
        backgroundColor = Color(0xfffeff9c)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
        ) {
        Text(text = note.title, style = MaterialTheme.typography.h6)
        Text(text = note.entryDate.toString(), style = MaterialTheme.typography.caption)
    }
        Text(text = note.description, style = MaterialTheme.typography.body1)
    }
    }
}