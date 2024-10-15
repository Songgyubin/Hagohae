package com.gyub.data.dao

import androidx.paging.PagingSource
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gyub.data.model.MissionEntity

/**
 * Mission Dao
 *
 * @author   Gyub
 * @created  2024/10/15
 */
interface MissionDao {

    @Query("SELECT * FROM mission")
    fun getMissions(): PagingSource<Int, MissionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMission(mission: MissionEntity)
}
