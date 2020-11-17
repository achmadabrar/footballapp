package com.achmadabrar.myapplication.data.database

import androidx.room.*
import com.achmadabrar.myapplication.data.database.converters.FavEventConverter
import com.achmadabrar.myapplication.data.models.FavModel
import java.util.*

@Dao
@TypeConverters(FavEventConverter::class)
interface FavEventDao {
    @Query("SELECT * FROM favorite_match_table")
    fun getAllFavEvent(): List<FavModel>?

    @Query("SELECT * FROM favorite_match_table WHERE `eventId` == :eventId ")
    fun getFavEvent(eventId: Long?): FavModel?

    @Query("DELETE FROM favorite_match_table WHERE eventId == :eventId")
    fun deleteFavEvent(eventId: Long?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavEvent(listFav: List<FavModel>)
}