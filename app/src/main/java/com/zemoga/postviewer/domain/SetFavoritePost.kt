package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.data.model.DetailsModel
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class SetFavoritePost @Inject constructor(
    private val repository: PostRepository
){
    suspend operator fun invoke(postId:String, isFavorite:Boolean) = repository.setPostAsFavorite(postId, isFavorite)
}