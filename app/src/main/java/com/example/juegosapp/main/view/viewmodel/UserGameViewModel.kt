package com.example.juegosapp.main.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.juegosapp.main.model.UserGame
import com.example.juegosapp.main.model.repository.UserGameRepository
import kotlinx.coroutines.launch


class UserGameViewModel (private val repository: UserGameRepository) : ViewModel()  {

    // devuelve todos los datos de la bd
    val allDatos: LiveData<List<UserGame>> = repository.allDatos

    // Launching a new coroutine to insert the data in a non-blocking way
    fun insert(frases: UserGame) = viewModelScope.launch {
        repository.insert(frases)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
    fun eliminarUno(id: Int) = viewModelScope.launch {
        repository.deleteUno(id)
    }

}

class UserGameViewModelFactory(private val repository: UserGameRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserGameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserGameViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}