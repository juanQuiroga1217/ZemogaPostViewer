package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.data.database.entities.toDatabase
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class GetPosts @Inject constructor(
    private val repository : PostRepository
){


    suspend operator fun invoke():List<Post>{
        val localPost = repository.getAllPostsFromDatabase()
        return if (localPost.isNullOrEmpty()){
            val posts = repository.getAllPostsFromApi()
            if (posts.isNotEmpty()) {
                repository.clearPosts()
                repository.insertPosts(posts.map { it.toDatabase() })
                posts
            } else{
                emptyList()
            }
        } else {
            localPost
        }
    }

}