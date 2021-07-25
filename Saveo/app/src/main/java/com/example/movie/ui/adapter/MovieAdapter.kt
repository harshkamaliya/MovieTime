package com.example.movie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie.R
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.ui.clickListener.MovieClickListener
import com.example.movie.ui.viewholder.MovieViewHolder

class MovieAdapter(private var moviesList : List<ResponseDTO>, private val itemClickListener: MovieClickListener):RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout_horizontal, parent, false)
        return MovieViewHolder(view,itemClickListener)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.setData(moviesList[position])

    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    fun updateData(movieList: List<ResponseDTO>) {
        this.moviesList = movieList
        notifyDataSetChanged()
    }


}