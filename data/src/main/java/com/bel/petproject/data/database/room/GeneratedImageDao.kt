package com.bel.petproject.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bel.petproject.data.models.GeneratedImageDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface GeneratedImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addGeneratedImage(generatedImageDbEntity: GeneratedImageDbEntity)

    @Delete
    suspend fun deleteGeneratedImage(generatedImageDbEntity: GeneratedImageDbEntity)

    @Query("Select * From generatedimagedbentity")
    fun subscribeChanges(): Flow<List<GeneratedImageDbEntity>>

}