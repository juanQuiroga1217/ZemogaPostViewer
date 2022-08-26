package com.zemoga.postviewer.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.zemoga.postviewer.domain.frontmodel.Post

@Entity(tableName = "posts_table")
data class PostEntity(
    @ColumnInfo(name = "userId")
    val userId: String,
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "postId")
    val postId: String,
    @ColumnInfo(name = "postTitle")
    val postTitle: String,
    @ColumnInfo(name = "postBody")
    val postBody: String,
    @ColumnInfo(name = "isFavorite")
    var isPostFavorite: Boolean
)

fun Post.toDatabase() = PostEntity(postId = postId, postTitle = title, isPostFavorite = isFavorite, postBody = postBody, userId = userId)