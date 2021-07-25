package com.example.movie.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.repository.PostDataRepository

class MoviesViewModelFactory(val repository:PostDataRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return PostDataViewModel(repository) as T

    }

}