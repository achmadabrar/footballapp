package com.achmadabrar.myapplication.data.database

import androidx.room.*
import com.achmadabrar.myapplication.data.database.table.PrevEvent

@Dao
@TypeConverters()
interface PrevEventDao {
    @Query("SELECT * FROM prev_event_table WHERE `idLeague` == :idLeague ")
    fun getPrevMatch(idLeague: Long?): List<PrevEvent>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPrevMatch(listPost: List<PrevEvent>)
}