package com.achmadabrar.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_match_table")
data class FavModel (
    @PrimaryKey val eventId: Long?,
    val homeLogo: String?,
    val awayLogo: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val statusMatch: String
)