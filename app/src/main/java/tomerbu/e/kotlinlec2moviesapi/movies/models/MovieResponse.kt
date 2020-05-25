package tomerbu.e.kotlinlec2moviesapi.movies.models

import com.google.gson.annotations.SerializedName

/**
 * Created by tomerbuzaglo on 24/05/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
class MovieResponse (
    @SerializedName("results")
    val movies: List<Movie>
)

//in the json we have results
//here we changed the name to movies.