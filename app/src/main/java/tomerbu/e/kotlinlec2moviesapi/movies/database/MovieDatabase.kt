package tomerbu.e.kotlinlec2moviesapi.movies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import tomerbu.e.kotlinlec2moviesapi.movies.models.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun moviesDao(): MoviesDao
    //dao has method insert, get

    //problem: kotlin don't have static keyword!
    //all static
    companion object {
        //var val fun
        fun create(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java,
                "movies"
            )
             //   .allowMainThreadQueries() //
                .build()
        }
    }
}
