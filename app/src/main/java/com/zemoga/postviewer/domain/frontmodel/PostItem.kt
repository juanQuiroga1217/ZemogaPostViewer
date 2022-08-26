package com.zemoga.postviewer.domain.frontmodel

import com.zemoga.postviewer.data.database.entities.PostEntity
import com.zemoga.postviewer.data.model.PostModel

data class Post (val title:String, var isFavorite:Boolean, val postId:String, val postBody:String, val userId:String)

fun PostModel.toDomain() = Post(postTitle, isPostFavorite, postId, postBody, userId)
fun PostEntity.toDomain() = Post(postTitle, isPostFavorite, postId, postBody, userId)