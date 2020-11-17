package com.achmadabrar.myapplication.core.di.modules

import android.app.Application
import androidx.room.Room
import com.achmadabrar.myapplication.data.database.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): FootballDatabase {
        return Room
            .databaseBuilder(application, FootballDatabase::class.java, FootballDatabase.DB_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideFavEventDao(appDataBase: FootballDatabase): FavEventDao {
        return appDataBase.favEventDao()
    }

    @Provides
    fun provideNextEventDao(appDataBase: FootballDatabase): NextEventDao {
        return appDataBase.nextEventDao()
    }

    @Provides
    fun providePrevEventDao(appDataBase: FootballDatabase): PrevEventDao {
        return appDataBase.prevEventDao()
    }

    @Provides
    fun provideListLeagueDao(appDataBase: FootballDatabase): ListLeagueDao {
        return appDataBase.listLeagueDao()
    }
}