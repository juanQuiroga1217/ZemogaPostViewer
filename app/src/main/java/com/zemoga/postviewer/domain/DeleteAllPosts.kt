package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.domain.frontmodel.Post
import javax.inject.Inject

class DeleteAllPosts @Inject constructor(
    private val repository: PostRepository
){
    suspend operator fun invoke(): List<Post>{
        repository.clearPosts()
        return listOf()
    }

}