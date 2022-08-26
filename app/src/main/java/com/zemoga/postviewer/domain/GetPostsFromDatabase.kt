package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class GetPostsFromDatabase @Inject constructor(
    private val repository: PostRepository
){
    suspend operator fun invoke(): List<Post> {
        val posts: List<Post> = repository.getAllPostsFromDatabase()
        if (!posts.isNullOrEmpty())
            return posts
        return emptyList()
    }
}