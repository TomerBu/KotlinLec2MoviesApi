package tomerbu.e.kotlinlec2moviesapi.movies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tomerbu.e.kotlinlec2moviesapi.movies.models.Movie

@Dao
interface MoviesDao {

    //if the movies already exist -> replace them
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)
    //compiler replaces it with INSERT INTO Movies(id,..) VALUES(...)

    @Query("SELECT * FROM movies")
    suspend fun getSavedMovies(): List<Movie>

    //fun haveFun(x:Int, y: Int): Int
}