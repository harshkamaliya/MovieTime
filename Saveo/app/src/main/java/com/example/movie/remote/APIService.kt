package com.example.movie.remote

import com.example.movie.constants.ConstantsData
import com.example.movie.dataModel.ShowDTO
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface APIService {


    // Get All Available Properties

    @Headers("Accept: application/json")
    @GET(ConstantsData.POSTS_END_POINT)
    suspend fun getAllPosts(
        @Header("Content-Type") contentType: String
    ) : ArrayList<ShowDTO>




}