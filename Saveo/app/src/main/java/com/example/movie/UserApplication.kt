package com.example.movie

import android.app.Application
import com.example.movie.repository.PostDataRepository

class UserApplication:Application() {

    val repository : PostDataRepository by lazy {
        PostDataRepository()
    }



}