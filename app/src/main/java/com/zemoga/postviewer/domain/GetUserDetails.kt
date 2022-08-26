package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.DetailsRepository
import com.zemoga.postviewer.data.model.UserModel
import javax.inject.Inject

class GetUserDetails @Inject constructor(
    private val repository: DetailsRepository
){
    suspend operator fun invoke(userId:String):UserModel? = repository.getUserDetails(userId)
}