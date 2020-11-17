package com.achmadabrar.myapplication.data.database

import androidx.room.*
import com.achmadabrar.myapplication.data.database.converters.NextEventConverter
import com.achmadabrar.myapplication.data.database.table.NextEvent
import java.util.*

@Dao
@TypeConverters(NextEventConverter::class)
interface NextEventDao {

    @Query("SELECT * FROM next_match_table WHERE `idLeague` == :idLeague ")
    fun getNextEvent(idLeague: Long?): List<NextEvent>?

    @Query("DELETE FROM next_match_table WHERE expiredDate < :expiredDate")
    fun deleteListLeague(expiredDate: Date)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNextEvent(listPost: List<NextEvent>)
}