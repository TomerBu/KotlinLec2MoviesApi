package tomerbu.e.kotlinlec2moviesapi.movies.api


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import tomerbu.e.kotlinlec2moviesapi.movies.models.MovieResponse

interface MoviesApiService {

    ///3/movie/popular
    //fun getPopular(...) //api key

    @GET("/3/movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse

    ///3/movie/popular?api_key=faf1c1f47ce8b75b085fededbb280341

    @GET("/3/movie/latest")
    suspend fun getLatestMovies(
        @Query("api_key") apiKey: String
    ): MovieResponse
}

//https://api.themoviedb.org/3/movie/popular?api_key=faf1c1f47ce8b75b085fededbb280341&language=en-US&page=1


//Movie posterPath
//Movie poster_path
