package tomerbu.e.kotlinlec2moviesapi.movies.api

import android.content.Context
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tomerbu.e.kotlinlec2moviesapi.movies.database.MovieDatabase
import tomerbu.e.kotlinlec2moviesapi.movies.models.Movie
import tomerbu.e.kotlinlec2moviesapi.movies.models.MovieResponse
import java.io.IOException

class MovieDataSource {

    companion object {
        val API_KEY: String = "faf1c1f47ce8b75b085fededbb280341"
    }

    // fun getPopMovies(listener: MCallBack){}

    suspend fun getPopularMovies(context: Context): List<Movie>? {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: MoviesApiService = retrofit.create(MoviesApiService::class.java)

        val dao = MovieDatabase.create(context).moviesDao()
        return try {
            val movies = service.getPopularMovies(API_KEY).movies
            dao.saveMovies(movies)
            movies
        } catch (exc: Throwable) {
            val movies = dao.getSavedMovies()
            if (exc is IOException && movies.isNotEmpty()){
                movies
            }else{
                throw exc
            }
        }

    }
}
//poster_path -> posterPath
//results -> movies