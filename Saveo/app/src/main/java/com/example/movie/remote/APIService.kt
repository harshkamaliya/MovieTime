package com.example.movie.remote

import com.example.movie.constants.ConstantsData
import com.example.movie.dataModel.ResponseDTO
import com.example.movie.dataModel.ShowDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIService {


    // Get All Available Properties

    @Headers("Accept: application/json")
    @GET(ConstantsData.POSTS_END_POINT)
    suspend fun getAllMoviePost(
        @Header("Content-Type") contentType: String,
        @Query("q") query: String,
    ) : ArrayList<ResponseDTO>








}