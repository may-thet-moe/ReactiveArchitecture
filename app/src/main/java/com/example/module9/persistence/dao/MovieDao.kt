package com.example.module9.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.module9.data.vos.MovieVO

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies : List<MovieVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSingleMovie (movie : MovieVO?)

    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<MovieVO>>

    @Query("SELECT * FROM movies WHERE id = :movieID")
    fun getMovieById(movieID : Int) : LiveData<MovieVO?>

    @Query("SELECT * FROM movies WHERE id = :movieID")
    fun getMovieByIdOneTime(movieID : Int) : MovieVO?

    @Query("SELECT * FROM movies WHERE type = :type")
    fun getMoviesByType(type : String): LiveData<List<MovieVO>>

    @Query("DELETE FROM movies")
    fun deleteAllMovies()
}