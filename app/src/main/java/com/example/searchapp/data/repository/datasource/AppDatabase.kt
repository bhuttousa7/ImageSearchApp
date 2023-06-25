package com.example.searchapp.data.repository.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.searchapp.data.DAO.ImageDao
import com.example.searchapp.data.model.ImageItem
import com.example.searchapp.data.model.ImageResponse

//@Database(entities = [ImageResponse::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao

//    companion object {
//        @Volatile
//        private var instance: AppDatabase? = null
//
//        fun getInstance(context: Context): AppDatabase {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also { instance = it }
//            }
//        }
//
//        private fun buildDatabase(context: Context): AppDatabase {
//            return Room.databaseBuilder(context, AppDatabase::class.java, "app_database.db")
//                .build()
//        }
//    }
}