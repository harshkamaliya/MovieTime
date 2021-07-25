package com.example.movie.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.ui.clickListener.MovieClickListener
import kotlinx.android.synthetic.main.item_layout_horizontal.view.*

class MovieViewHolder(private val view:View, private val itemClickListener : MovieClickListener):RecyclerView.ViewHolder(view) {


    fun setData(movieData : ResponseDTO){
        view.apply {

            Glide.with(this)
                .load(movieData.show?.image?.medium)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)

            imageView.setOnClickListener {
                itemClickListener.onItemClick(movieData)
            }

        }
    }


}