package tomerbu.e.kotlinlec2moviesapi.movies.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*
import tomerbu.e.kotlinlec2moviesapi.R
import tomerbu.e.kotlinlec2moviesapi.movies.models.Movie

/**
 * Created by tomerbuzaglo on 24/05/2020.
 * Copyright 2020 tomerbuzaglo. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0
 * you may not use this file except
 * in compliance with the License
 */
class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>() {

    //property: ArrayList<Movie>
    private var items = mutableListOf<Movie>() //kotlin collections google photos

    fun setData(newItems: List<Movie>){
        items.clear()
        items.addAll(newItems)
        //notify the adapter about the changes.
        notifyDataSetChanged() //tableView.reloadData()
    }

    //1) inner class View Holder:
    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //lateinit var tvTitle: TextView

        fun populate(movie: Movie) {

            //var v:TextView = itemView.findViewById(R.id.tvTitle)

            with(itemView) {
                tvTitle.text = movie.title
                Picasso.get().load(movie.getPoster()).into(ivPoster)
            }


            //found view by id .text = :
           // itemView.tvTitle.text = movie.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = items[position]
        holder.populate(item)
    }
}