package com.example.juegosapp.main.model.repository
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.juegosapp.main.model.UserGame
import com.example.juegosapp.main.model.UserGameDao

class UserGameRepository(private val userGameDao: UserGameDao) {

    val allDatos: LiveData<List<UserGame>> = userGameDao.getAllDatos()

    @WorkerThread
    suspend fun insert(userGame: UserGame) {
        userGameDao.insert(userGame)
    }
    @WorkerThread
    suspend fun deleteAll() {
        userGameDao.deleteAll()
    }
    @WorkerThread
    suspend fun deleteUno(id:Int) {
        userGameDao.deleteUno(id)
    }


}