package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class GetFavoritePosts @Inject constructor(
    private val repository: PostRepository
){

    suspend operator fun invoke(): List<Post> {
        val posts: List<Post> = repository.getAllPostsFromDatabase().filter { it.isFavorite }
        if (!posts.isNullOrEmpty())
            return posts
        return emptyList()
    }

}