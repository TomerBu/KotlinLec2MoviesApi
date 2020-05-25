package tomerbu.e.kotlinlec2moviesapi.movies.models

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import tomerbu.e.kotlinlec2moviesapi.movies.api.MovieDataSource

class MovieViewModel(app: Application) : AndroidViewModel(app) {
    //how does a View Model report errors?

    //lazy var:
    //1) init when called
    //2) block that inits the property

    private val movies: MutableLiveData<List<Movie>> by lazy {
        //init block for the property:
        viewModelScope.launch {
            refreshMovies()
        }
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

    private suspend fun refreshMovies() {
        //async work (repo get data)
        try {
            val moviesFromDS = MovieDataSource().getPopularMovies(getApplication())
            movies.postValue(moviesFromDS)
        } catch (exc: Throwable) {
            moviesError.postValue(exc)
        }
    }
    //show data nicely
    //init to get the movies / exc that the fragment needs
}
