package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.DetailsRepository
import com.zemoga.postviewer.data.model.ComentsModel
import javax.inject.Inject

class GetPostComments @Inject constructor(
    private val repository: DetailsRepository
){
    suspend operator fun invoke(postId:String):List<ComentsModel> = repository.getPostComments(postId)
}