package com.achmadabrar.myapplication.data.database

import androidx.room.*
import com.achmadabrar.myapplication.data.database.converters.LeagueConverter
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import java.util.*

@Dao
@TypeConverters(LeagueConverter::class)
interface ListLeagueDao {
    @Query("SELECT * FROM league_table")
    fun getAllLeague(): List<LeagueUiModel>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertLeague(listPost: List<LeagueUiModel>)

    @Query("DELETE FROM league_table WHERE expiredDate < :expiredDate")
    fun deleteListLeague(expiredDate: Date)
}