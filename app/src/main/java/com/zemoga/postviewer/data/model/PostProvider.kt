package com.zemoga.postviewer.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PostProvider @Inject constructor() {
    var posts: List<PostModel> = emptyList()
}