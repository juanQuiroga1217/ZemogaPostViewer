package com.zemoga.postviewer.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zemoga.postviewer.data.database.dataaccessobject.PostsDao
import com.zemoga.postviewer.data.database.entities.PostEntity

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase:RoomDatabase() {

    abstract fun getPostDao():PostsDao

}