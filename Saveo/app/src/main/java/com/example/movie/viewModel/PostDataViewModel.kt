package com.example.movie.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.dataModel.ShowDTO
import com.example.movie.remote.Resource
import com.example.movie.repository.PostDataRepository
import kotlinx.coroutines.Dispatchers

class PostDataViewModel(private val repo : PostDataRepository):ViewModel()
{


    fun getPosts() : LiveData<List<ResponseDTO>> {

        return liveData(Dispatchers.IO){
            val result : Resource<List<ResponseDTO>> = repo.getPosts()

            emit(result.data!!)
        }
    }




}