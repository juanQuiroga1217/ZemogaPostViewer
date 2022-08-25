package com.zemoga.postviewer.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.postviewer.data.model.PostModel
import com.zemoga.postviewer.domain.GetFavoritePosts
import com.zemoga.postviewer.domain.GetPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase:GetPosts,
    private val getFavoritePosts: GetFavoritePosts
): ViewModel() {

    val postModel = MutableLiveData<List<PostModel>?>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPostsUseCase()

            if (!result.isNullOrEmpty())
                result[0].isPostFavorite = true
                postModel.postValue(result)
                isLoading.postValue(false)
        }
    }

    fun listFavoritePosts() {
        isLoading.postValue(true)
        val favoritePosts = getFavoritePosts()
        postModel.postValue(favoritePosts)
        isLoading.postValue(false)
    }

    fun listAllPosts() {
        isLoading.postValue(true)

        isLoading.postValue(false)
    }


}