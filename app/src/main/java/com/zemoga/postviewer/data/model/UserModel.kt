package com.zemoga.postviewer.data.model

import com.google.gson.annotations.SerializedName

data class UserModel (
    @SerializedName("name")
    val userName:String,
    @SerializedName("email")
    val userEmail:String,
    @SerializedName("phone")
    val userPhone:String,
    @SerializedName("website")
    val userWebsite:String
    )