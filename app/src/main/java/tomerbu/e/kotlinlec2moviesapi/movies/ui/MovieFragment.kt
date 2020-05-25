package tomerbu.e.kotlinlec2moviesapi.movies.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movie_fragment.*
import tomerbu.e.kotlinlec2moviesapi.movies.models.MovieViewModel
import tomerbu.e.kotlinlec2moviesapi.R
import tomerbu.e.kotlinlec2moviesapi.movies.api.MovieDataSource


class MovieFragment : Fragment() {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movie_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieAdapter = MovieAdapter()
        rvMovies.adapter = movieAdapter
        rvMovies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        viewModel.getMovies().observe(viewLifecycleOwner, Observer {

            //setData(movies)
            movieAdapter.setData(it)
        })
    }
}
