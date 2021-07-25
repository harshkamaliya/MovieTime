package com.example.movie.repository

import com.example.movie.dataModel.PageResponseDTO
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.dataModel.ShowDTO
import com.example.movie.remote.APIService
import com.example.movie.remote.Resource
import com.example.movie.remote.ResponseHandler
import com.example.movie.remote.RetrofitGenerator

class PostDataRepository() {

    private val CONTENT_TYPE = "application/json"

    private val api : APIService = RetrofitGenerator.getInstance().create(APIService::class.java)
    private val responseHandler = ResponseHandler()

    suspend fun getPosts() : Resource<List<ResponseDTO>> {
        val result : List<ResponseDTO> = api.getAllMoviePost(CONTENT_TYPE,"1")
        return  responseHandler.handleSuccess(result)
    }

//    suspend fun getMoviewByPage() : Resource<List<PageResponseDTO>>{
//
//        val result : List<PageResponseDTO> = api.getMoviewByPage(CONTENT_TYPE, "1")
//
//        return  responseHandler.handleSuccess(result)
//
//    }
























}