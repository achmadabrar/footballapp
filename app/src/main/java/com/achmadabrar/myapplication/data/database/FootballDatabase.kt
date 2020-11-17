package com.achmadabrar.myapplication.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.achmadabrar.myapplication.data.database.converters.DateTypeConverter
import com.achmadabrar.myapplication.data.database.table.NextEvent
import com.achmadabrar.myapplication.data.database.table.PrevEvent
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.FavModel
import com.achmadabrar.myapplication.data.models.LeagueUiModel

@Database(
    entities = [NextEvent::class, PrevEvent::class, LeagueUiModel::class, Event::class, FavModel::class],
    version = 4,
    exportSchema = true
)
@TypeConverters(DateTypeConverter::class)
abstract class FootballDatabase: RoomDatabase() {

    abstract fun nextEventDao(): NextEventDao
    abstract fun favEventDao(): FavEventDao
    abstract fun prevEventDao(): PrevEventDao
    abstract fun listLeagueDao(): ListLeagueDao

    companion object {

        private var instance: FootballDatabase? = null
        private val LOCK = Any()
        const val DB_NAME = "footbalmatch.db"

        @JvmStatic
        fun getInstance(context: Context): FootballDatabase {
            synchronized(LOCK) {
                if (instance == null) {
                    instance = Room
                        .databaseBuilder(
                            context.applicationContext,
                            FootballDatabase::class.java,
                            DB_NAME
                        )
                        .build()
                }
                return instance!!
            }
        }
    }
}