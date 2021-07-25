package com.example.movie.ui

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.R
import com.example.movie.UserApplication
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.repository.PostDataRepository
import com.example.movie.ui.adapter.MovieAdapter
import com.example.movie.ui.clickListener.MovieClickListener
import com.example.movie.viewModel.MoviesViewModelFactory
import com.example.movie.viewModel.PostDataViewModel
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieClickListener {

    private lateinit var moviesAdapter: MovieAdapter
    private var moviesListData: List<ResponseDTO> = listOf()

    private lateinit var imageSlider: ImageSlider

    private var imageUrls = ArrayList<String>()

    private lateinit var viewModel: PostDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerAdatapter()
        imageSlider = image_slider

        val appObj = application as UserApplication

        val reposotory: PostDataRepository = appObj.repository
        val viewModelFactory: MoviesViewModelFactory = MoviesViewModelFactory(reposotory)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDataViewModel::class.java)

        if (isNetworkConnected()) {
            viewModel.getPosts().observe(this, {

                val image: List<ResponseDTO> =it!!

                image.forEach { responseDTO ->
                    responseDTO.show?.image?.original?.let { it1 -> imageUrls.add(it1) }
                }

                imageSlider.adapter?.notifyDataSetChanged()
            })

            imageUrls.add("http://i.imgur.com/CqmBjo5.jpg")

            imageSlider.adapter = SliderAdapter(
                this,
                PicassoImageLoaderFactory(),
                imageUrls = imageUrls
            )



        }


    }

    private fun setRecyclerAdatapter() {

        moviesAdapter = MovieAdapter(moviesListData, this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvVertical.layoutManager = gridLayoutManager
        rvVertical.adapter = moviesAdapter
    }

    override fun onItemClick(data: ResponseDTO) {


    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }
}