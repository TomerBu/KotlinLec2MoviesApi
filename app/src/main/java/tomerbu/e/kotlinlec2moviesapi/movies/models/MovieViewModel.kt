package tomerbu.e.kotlinlec2moviesapi.movies.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tomerbu.e.kotlinlec2moviesapi.movies.api.MovieDataSource
import java.lang.Exception

class MovieViewModel(app: Application) : AndroidViewModel(app) {
    //how does a View Model report errors?

    //lazy var:
    //1) init when called
    //2) block that inits the property

    private val movies: MutableLiveData<List<Movie>> by lazy {
        //init block for the property:
        refreshMovies()
        return@lazy MutableLiveData<List<Movie>>()
    }

    //return read only version of the live data //fragment cant live.value =
    fun getMovies(): LiveData<List<Movie>> {
        return movies
    }

    private val moviesError: MutableLiveData<Throwable> = MutableLiveData()
    fun getError(): LiveData<Throwable> {
        return moviesError
    }

    private fun refreshMovies() {
        //async work (repo get data)
        MovieDataSource().getPopularMovies(onMoviesReceived = {
            //update the live data:
            movies.postValue(it)
        }, onError = {
            moviesError.postValue(it)
        }, context = getApplication())
    }
    //show data nicely
    //init to get the movies / exc that the fragment needs
}
