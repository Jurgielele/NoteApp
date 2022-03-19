package com.example.mynoteapp.screens.noteScreen

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mynoteapp.model.Note
import com.example.mynoteapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@ExperimentalComposeUiApi
@HiltViewModel
class AddEditNoteViewModel @Inject constructor(private val repository: NoteRepository): ViewModel() {
    private var title = mutableStateOf("")
    private var description = mutableStateOf("")
    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    private val x = false
    val noteList = _noteList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllNotes().distinctUntilChanged()
                .collect { listOfNotes ->
                    if (listOfNotes.isNullOrEmpty()) {
                        Log.d("Empty", ": Empty list")
                    }else {
                        _noteList.value = listOfNotes
                    }

                }

        }
        // noteList.addAll(NotesDataSource().loadNotes())
    }
    fun onTitleChange(t: String) {title.value = t}
    fun onDescriptionChange(d: String) {description.value = d}

    fun getTitle(): String{
        return title.value
    }
    fun getDescription(): String{
        return description.value
    }

    fun getNoteById(id: UUID) {
        if(!x){
            val note = noteList.value.find { it.id == id }
            description.value = note?.description ?: ""
            title.value = note?.title ?: ""
        }
    }

    fun addNote(note: Note) = viewModelScope.launch { repository.addNote(note) }
    fun updateNote(id: UUID) {

        viewModelScope.launch { repository.updateNote(Note(id = id, title=getTitle(),description = getDescription())) }
    }
    fun removeNote(note: Note) = viewModelScope.launch { repository.deleteNote(note) }
}

