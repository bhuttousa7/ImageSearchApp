package com.example.searchapp.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.searchapp.data.model.ImageResponse

@Dao
interface ImageDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun saveImage(image: ImageResponse)
//
//    @Query("SELECT * FROM images")
//    suspend fun getImages(): List<ImageResponse>
}