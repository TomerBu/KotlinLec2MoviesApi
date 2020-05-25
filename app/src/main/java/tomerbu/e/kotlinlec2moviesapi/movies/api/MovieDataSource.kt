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

    fun getPopularMovies(
        onMoviesReceived: (List<Movie>) -> Unit,
        onError: (Throwable) -> Unit,
        context: Context
    ) {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: MoviesApiService = retrofit.create(MoviesApiService::class.java)


        /*No Lambda -> anonymous*/
        //run request in the background, callback on the ui thread.
        service.getPopularMovies(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                GlobalScope.launch(Dispatchers.IO) {
                    val movies = MovieDatabase.create(context).moviesDao().getSavedMovies()
                    if (t is IOException && movies.isNotEmpty()) {
                        GlobalScope.launch(Dispatchers.Main) {
                            //use the cached movies
                            onMoviesReceived(movies) //background fetch -> UI Thread
                        }
                        //kotlin co-routines
                    } else {
                        onError(t) //tell the callback
                    }
                }
            }

            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                val movies = response.body()?.movies ?: emptyList()

                if (movies.isNotEmpty()) {
                    //background:
                    GlobalScope.launch(Dispatchers.IO) {
                        //code that runs in the background
                        //Save movies to the database for better UX.
                        MovieDatabase.create(context).moviesDao().saveMovies(movies)
                    }
                    //tell the callback
                    onMoviesReceived(movies)
                }
            }
        })
    }
}
//poster_path -> posterPath
//results -> movies