package com.example.android.justeat.ui.movie.post

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.android.justeat.R
import com.example.android.justeat.model.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>(){
    private lateinit var movieList: List<Movie>

    override fun onCreateViewHolder(parent: ViewGroup, viewType:Int): MovieListAdapter.ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListAdapter.ViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun getItemCount(): Int {
        return if(::movieList.isInitialized) movieList.size else 0
    }

    fun updateMovieList(postList:List<Movie>){
        this.movieList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemMovieBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel= MovieViewModel()

        fun bind(movie:Movie){
            viewModel.bind(movie)
            binding.viewodel = viewModel
        }
    }
}