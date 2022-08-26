package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.DetailsRepository
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class DeletePost@Inject constructor(
    private val repository: DetailsRepository
){
    suspend operator fun invoke(postId:String){
        repository.deletePost(postId)
    }

}