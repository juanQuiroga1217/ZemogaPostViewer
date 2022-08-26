package com.zemoga.postviewer.data.network

import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.data.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailsService @Inject constructor(
    private val api:PostApiClient
) {

    suspend fun getUserDetails(userId: String): UserModel?{
        return withContext(Dispatchers.IO) {
            val response = api.getUser(userId)
            response.body()
        }
    }

    suspend fun getPostComments(postId: String): List<ComentsModel>{
        return withContext(Dispatchers.IO) {
            val response = api.getPostComments(postId)
            response.body()?: emptyList()
        }
    }
}