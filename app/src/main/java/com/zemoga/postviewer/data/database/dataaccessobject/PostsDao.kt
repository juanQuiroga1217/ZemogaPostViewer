package com.zemoga.postviewer.data.database.dataaccessobject

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.zemoga.postviewer.data.database.entities.PostEntity

@Dao
interface PostsDao {

    @Query("SELECT * FROM posts_table ORDER BY isFavorite DESC")
    suspend fun getAllPosts():List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(posts:List<PostEntity>)

    @Query("DELETE FROM posts_table")
    suspend fun deleteAllPosts()

    @Query("UPDATE posts_table SET isFavorite = :setFavorite WHERE postId =:postId")
    suspend fun updateFavorite(setFavorite: Boolean, postId: String)

    @Query("DELETE FROM posts_table WHERE postId =:postId")
    suspend fun deletePost(postId: String)
}