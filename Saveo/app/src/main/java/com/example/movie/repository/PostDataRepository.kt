package com.example.movie.repository

import com.example.movie.dataModel.ShowDTO
import com.example.movie.remote.APIService
import com.example.movie.remote.Resource
import com.example.movie.remote.ResponseHandler
import com.example.movie.remote.RetrofitGenerator

class PostDataRepository() {

    private val CONTENT_TYPE = "application/json"

    private val api : APIService = RetrofitGenerator.getInstance().create(APIService::class.java)
    private val responseHandler = ResponseHandler()

    suspend fun getPosts() : Resource<List<ShowDTO>> {
        val result : List<ShowDTO> = api.getAllPosts(CONTENT_TYPE)
        return  responseHandler.handleSuccess(result)
    }



//    suspend fun getMoviewByPage() : Resource<List<ShowDTO>>{
//        val result : List<ShowDTO> = api.getMoviewByPage(CONTENT_TYPE, "1")
//        return  responseHandler.handleSuccess(result)
//    }





















}