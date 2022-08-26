package com.zemoga.postviewer.domain

import com.zemoga.postviewer.data.model.ComentsModel
import com.zemoga.postviewer.data.model.DetailsModel
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.data.model.UserModel
import javax.inject.Inject

class DetailsProvider @Inject constructor(){
    operator fun invoke(post:PostModel, userDetails: UserModel?, comments: List<ComentsModel>):DetailsModel = DetailsModel(post, userDetails, comments)
}