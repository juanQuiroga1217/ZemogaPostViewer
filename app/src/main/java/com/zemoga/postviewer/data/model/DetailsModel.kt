package com.zemoga.postviewer.data.model

data class DetailsModel(
    val post:PostModel,
    val user: UserModel?,
    val comments:List<ComentsModel>
)