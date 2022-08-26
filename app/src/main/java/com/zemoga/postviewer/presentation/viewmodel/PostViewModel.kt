package com.zemoga.postviewer.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zemoga.postviewer.domain.*
import com.zemoga.postviewer.domain.frontmodel.Post
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase:GetPosts,
    private val getFavoritePosts: GetFavoritePosts,
    private val getPostsFromDatabase: GetPostsFromDatabase,
    private val deleteAllPostsUseCase: DeleteAllPosts
): ViewModel() {

    val postModel = MutableLiveData<List<Post>>()
    val isLoading = MutableLiveData<Boolean>()
    val alert = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getPostsUseCase()
            if (!result.isNullOrEmpty()){
                postModel.postValue(result)
            }else{
                showConnectionFailed()
            }
            isLoading.postValue(false)
        }
    }

    fun listAllPosts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val posts = getPostsFromDatabase()
            postModel.postValue(posts)
            isLoading.postValue(false)
        }
    }

    fun listFavoritePosts() {
        viewModelScope.launch{
            isLoading.postValue(true)
            val favoritePosts = getFavoritePosts()
            postModel.postValue(favoritePosts)
            isLoading.postValue(false)
        }
    }

    fun deleteAllPosts() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = deleteAllPostsUseCase()
            postModel.postValue(result)
            isLoading.postValue(false)
        }
    }

    fun showConnectionFailed() {
        alert.postValue(true)
    }


}