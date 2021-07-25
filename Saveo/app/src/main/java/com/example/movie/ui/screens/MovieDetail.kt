package com.example.movie.ui.screens

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.movie.R
import com.example.movie.constants.ConstantsData
import com.example.movie.constants.Utils
import com.example.movie.dataModel.ResponseDTO
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        assert(
            supportActionBar != null //null check
        )
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        val inData = intent.getSerializableExtra(ConstantsData.DATA_KEY) as? ResponseDTO
        if (inData != null) {
            localData(inData)
        }


    }

    private fun localData(inData: ResponseDTO) {
        Glide.with(this).load(inData.show?.image?.medium).diskCacheStrategy(DiskCacheStrategy.ALL).into(ivDetailsImage)
        tvBg.text=inData.show?.name
        tv_movie_description.text= Html.fromHtml(inData.show?.summary)
        movieRating.numStars=5
        movieRating.stepSize=0.5F
        movieRating.rating= ((inData.show?.rating?.average as Float?)!!)
        ratingValue.text="${inData.show?.rating?.average!!}"
        movieTitle.text="${inData.show?.genres}"
        movieTime.text="${inData.show?.type}"
        movieDate.text="${inData.show?.averageRuntime?.let { Utils.getTimeConverted(it*60) }}"


    }

    override fun onSupportNavigateUp(): Boolean {
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.raw.exit_first, R.raw.exit_second)
        finish()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
        overridePendingTransition(R.raw.exit_first, R.raw.exit_second)
        finish()
    }

}