package com.example.juegosapp.main.model
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface UserGameDao {

    @Query("SELECT * FROM TABLE_USERGAME ORDER BY id ASC")
    fun getAllDatos(): LiveData<List<UserGame>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userGame: UserGame)

    @Query("DELETE FROM TABLE_USERGAME")
    suspend fun deleteAll()

    @Query("DELETE FROM TABLE_USERGAME where id=:id")
    suspend fun deleteUno(id: Int)
}
