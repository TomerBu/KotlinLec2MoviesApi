package tomerbu.e.kotlinlec2moviesapi.movies.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * Created by tomerbuzaglo on 24/05/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */

//properties in the constructor ():


//1) gradle
//2) Entities: Movie
//3) Dao
//optionally set the table name to movies.
@Entity(tableName = "movies")
class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val popularity:Double,

    //tell gson that poster_path = posterPath
    @SerializedName("poster_path")
    val posterPath: String
) {
    fun getPoster():String{
        return "https://image.tmdb.org/t/p/w500$posterPath"
    }
}

//data class:
//toString
//hashcode