package com.zemoga.postviewer.data.network

import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.data.model.UserModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PostApiClient {

    @GET("/posts")
    suspend fun getAllPosts():Response<List<PostModel>>

    @GET("/users/{userId}")
    suspend fun getUser(@Path("userId") userId:String):Response<UserModel>

    @GET("/posts/{postId}/comments")
    suspend fun getPostComments(@Path("postId") postId:String):Response<List<ComentsModel>>
}