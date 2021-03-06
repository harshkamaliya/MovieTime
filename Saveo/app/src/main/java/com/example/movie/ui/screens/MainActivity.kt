package com.example.movie.ui.screens

import android.content.BroadcastReceiver
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.Broadcast.MyNetworkBroadCast
import com.example.movie.R
import com.example.movie.UserApplication
import com.example.movie.constants.ConstantsData
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.repository.PostDataRepository
import com.example.movie.ui.adapter.MovieAdapter
import com.example.movie.ui.clickListener.MovieClickListener
import com.example.movie.ui.clickListener.NetworkListener
import com.example.movie.viewModel.MoviesViewModelFactory
import com.example.movie.viewModel.PostDataViewModel
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MovieClickListener, NetworkListener {
    private val TAG = "tag"
    private lateinit var moviesAdapter: MovieAdapter
    private var moviesListData: List<ResponseDTO> = listOf()
    private lateinit var imageSlider: ImageSlider
    private var mNetworkReceiver: BroadcastReceiver? = null

    private var imageUrls = ArrayList<String>()
    private lateinit var viewModel: PostDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setRecyclerAdapter()
        imageSlider = image_slider
        mNetworkReceiver = MyNetworkBroadCast(this, this)
        registerReceiver(mNetworkReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))


        val appObj = application as UserApplication
        val repository: PostDataRepository = appObj.repository
        val viewModelFactory: MoviesViewModelFactory = MoviesViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostDataViewModel::class.java)


        if (isNetworkConnected()) {
            viewModel.getPosts().observe(this, {
                val image: List<ResponseDTO> = it!!
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

        if (isNetworkConnected()) {
            viewModel.getPosts().observe(this, {
                val resp: List<ResponseDTO> = it!!
                Log.d(TAG, "Response is ${resp.size}")
                moviesListData = resp;
                moviesAdapter.updateData(resp)
            })
        } else {
            Toast.makeText(this, " No Internet ", Toast.LENGTH_SHORT)
                .show()
        }


    }


    override fun onBackPressed() {
        showCloseAppDialogue()
    }

    private fun showCloseAppDialogue() {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Close App ?")
        builder.setMessage("Do You Wants to Close the App")

        builder.setPositiveButton("YES", DialogInterface.OnClickListener { dialog, id ->
            finish()
        })

        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()

        })

        builder.setCancelable(true)
        builder.show()
    }

    private fun setRecyclerAdapter() {
        moviesAdapter = MovieAdapter(moviesListData, this)
        val gridLayoutManager = GridLayoutManager(this, 3)
        rvVertical.layoutManager = gridLayoutManager
        rvVertical.adapter = moviesAdapter
    }

    override fun onItemClick(data: ResponseDTO) {
        Log.d(TAG, "Movie Clicked is ${data.show?.externals?.tvrage}")
        goToMovieDetails(data)

    }

    private fun goToMovieDetails(movieData: ResponseDTO) {

        intent = Intent(this, MovieDetail::class.java)
        intent.putExtra(ConstantsData.DATA_KEY, movieData)
        startActivity(intent);
        overridePendingTransition(R.raw.enter_first, R.raw.enter_second);
       // finish();
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!.isConnected

    }


    override fun onNetworkChangeUpdates(data: String) {
        Log.d(TAG, "Network is ${data}")

        if (moviesListData.isEmpty() && data=="YES") {

            Toast.makeText(this, "Network Arrived Requesting Data ", Toast.LENGTH_SHORT)
                .show()

            viewModel.getPosts().observe(this, {

                val resp: List<ResponseDTO> = it!!

                Log.d(TAG, "Response is ${resp.size}")
                moviesListData = resp;
                moviesAdapter.updateData(resp)


            })
        }


    }
}