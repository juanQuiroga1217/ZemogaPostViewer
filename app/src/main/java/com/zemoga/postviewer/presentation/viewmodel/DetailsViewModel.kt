package com.zemoga.postviewer.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.postviewer.data.PostRepository
import com.zemoga.postviewer.data.model.DetailsModel
import com.zemoga.postviewer.data.model.toData
import com.zemoga.postviewer.domain.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostComments: GetPostComments,
    private val getUserDetails: GetUserDetails,
    private val detailsProvider: DetailsProvider,
    private val getPostsFromDatabase: GetPostsFromDatabase,
    private val setFavoritePost: SetFavoritePost,
    private val deletePostUseCase: DeletePost
) : ViewModel(){

    val detailsModel = MutableLiveData<DetailsModel?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreateDetails(postId: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val post = getPostsFromDatabase().filter { it.postId == postId }
            val userDetails = getUserDetails(post[0].userId)
            val postComments = getPostComments(post[0].postId)
            val result = detailsProvider(post[0].toData(), userDetails, postComments)
            detailsModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun toggleFavorite(favoriteDetailsModel: DetailsModel) {
        viewModelScope.launch {
            isLoading.postValue(true)
            favoriteDetailsModel.post.isPostFavorite = !favoriteDetailsModel.post.isPostFavorite
            setFavoritePost(favoriteDetailsModel.post.postId, favoriteDetailsModel.post.isPostFavorite)
            detailsModel.postValue(favoriteDetailsModel)
            isLoading.postValue(false)
        }
    }

    fun deletePost(postId: String?) {
        viewModelScope.launch {
            isLoading.postValue(true)
            if (postId != null) {
                deletePostUseCase(postId)
            }
            isLoading.postValue(false)
        }
    }

}