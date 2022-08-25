package com.zemoga.postviewer.data

import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.data.model.PostProvider
import com.zemoga.postviewer.data.network.PostService
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api : PostService,
    private val postProvider: PostProvider
){



    suspend fun getAllPosts():List<PostModel>{
        val response: List<PostModel> = api.getPosts()
        postProvider.posts = response
        return response
    }
}