package com.achmadabrar.myapplication.data.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "next_match_table")
data class NextEvent (
    @PrimaryKey val eventId: Long?,
    val idLeague: Long?,
    val matchTitle: String?,
    val statusMatch: String?,
    val matchPoster: String?,
    val venue: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?,
    val expiredDate: Date?
)