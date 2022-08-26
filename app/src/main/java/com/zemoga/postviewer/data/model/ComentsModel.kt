package com.zemoga.postviewer.data.model

import com.google.gson.annotations.SerializedName

data class ComentsModel(
    @SerializedName("postId")
    val postId:String,
    @SerializedName("id")
    val commentId:String,
    @SerializedName("name")
    val commentName:String,
    @SerializedName("body")
    val commentBody:String,
    @SerializedName("email")
    val commentEmail:String
)