package com.zemoga.postviewer.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.zemoga.postviewer.data.database.dataaccessobject.PostsDao
import com.zemoga.postviewer.data.database.entities.PostEntity
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.data.network.PostService
import com.zemoga.postviewer.domain.frontmodel.Post
import com.zemoga.postviewer.domain.frontmodel.toDomain
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostRepository @Inject constructor(
    private val api : PostService,
    private val postsDao: PostsDao,
    @ApplicationContext val context: Context
){

    suspend fun getAllPostsFromApi():List<Post>{
        return if (isOnline()){
            val response: List<PostModel> = api.getPosts()
            response.map { it.toDomain() }
        } else {
            emptyList()
        }
    }

    suspend fun getAllPostsFromDatabase():List<Post>{
        val response = postsDao.getAllPosts()
        return response.map { it.toDomain() }
    }

    suspend fun insertPosts(posts:List<PostEntity>) {
        postsDao.insertAll(posts)
    }

    suspend fun clearPosts() {
        postsDao.deleteAllPosts()
    }

    suspend fun setPostAsFavorite(post:String, isFavorite:Boolean){
        postsDao.updateFavorite(isFavorite, post)
    }

    private fun isOnline():Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                return true
            }
        }
        return false
    }
}