package com.zemoga.postviewer.data.network

import com.zemoga.postviewer.data.model.PostModel
import retrofit2.Response
import retrofit2.http.GET

interface PostApiClient {

    @GET("/posts")
    suspend fun getAllPosts():Response<List<PostModel>>
}