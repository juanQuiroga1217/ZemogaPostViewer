package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.data.model.PostProvider
import javax.inject.Inject

class GetFavoritePosts @Inject constructor(
    private val postProvider: PostProvider
){

    operator fun invoke(): List<PostModel> {
        val posts: List<PostModel> = postProvider.posts.filter { it.isPostFavorite }
        if (!posts.isNullOrEmpty())
            return posts
        return emptyList()
    }

}