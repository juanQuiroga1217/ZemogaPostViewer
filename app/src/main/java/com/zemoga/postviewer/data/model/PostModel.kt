package com.zemoga.postviewer.data.model

import com.google.gson.annotations.SerializedName

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
) {
}