package com.zemoga.postviewer.data

import com.zemoga.postviewer.data.database.dataaccessobject.PostsDao
import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.data.model.UserModel
import com.zemoga.postviewer.data.network.DetailsService
import javax.inject.Inject

class DetailsRepository @Inject constructor(
    private val api : DetailsService,
    private val postsDao: PostsDao
) {

    suspend fun getUserDetails(userId: String): UserModel? {
        return api.getUserDetails(userId)
    }

    suspend fun getPostComments(postId:String): List<ComentsModel> {
        return api.getPostComments(postId)
    }

    suspend fun deletePost(postId: String) {
        postsDao.deletePost(postId)
    }

}