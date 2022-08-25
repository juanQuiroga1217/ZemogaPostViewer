package com.zemoga.postviewer.data.network

import com.zemoga.postviewer.core.RetroFitHelper
import com.zemoga.postviewer.data.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostService @Inject constructor(private val api:PostApiClient){


    suspend fun getPosts(): List<PostModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllPosts()
            response.body() ?: emptyList()
        }

    }
}