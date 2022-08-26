package com.zemoga.postviewer.data.model

import com.google.gson.annotations.SerializedName
import com.zemoga.postviewer.domain.frontmodel.Post

data class PostModel(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("id")
    val postId: String,
    @SerializedName("title")
    val postTitle: String,
    @SerializedName("body")
    val postBody: String,
    var isPostFavorite: Boolean
)

fun Post.toData() = PostModel(postTitle = title, userId = userId, postId = postId, postBody = postBody, isPostFavorite = isFavorite)
