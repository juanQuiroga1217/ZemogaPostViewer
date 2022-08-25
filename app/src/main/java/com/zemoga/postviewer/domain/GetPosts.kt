package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.data.model.PostModel
import javax.inject.Inject

class GetPosts @Inject constructor(
    private val repository : PostRepository
){


    suspend operator fun invoke():List<PostModel>? = repository.getAllPosts()

}