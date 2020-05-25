package tomerbu.e.kotlinlec2moviesapi.movies.models

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tomerbu.e.kotlinlec2moviesapi.movies.api.MovieDataSource
import java.net.URL

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

            val jobResult = demoWork()
            println(jobResult)
            val currentThread = Thread.currentThread()
            println(currentThread)
        } catch (exc: Throwable) {
            moviesError.postValue(exc)
        }
    }

    suspend fun demoWork(): Int? {
        var result: Int? = null
        println("Start")
        withContext(Dispatchers.IO) {
            delay(1000)
            val t = Thread.currentThread()
            println(t)
            println("Done")
            result = getFakeResultFromWeb()
        }
        return result
    }

    private fun getFakeResultFromWeb(): Int{
        return 10
    }
    //show data nicely
    //init to get the movies / exc that the fragment needs
}
