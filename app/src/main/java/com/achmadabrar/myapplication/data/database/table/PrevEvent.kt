package com.achmadabrar.myapplication.data.database.table

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "prev_event_table")
data class PrevEvent (
    @PrimaryKey val eventId: Long,
    val idLeague: Long?,
    val matchTitle: String?,
    val statusMatch: String?,
    val venue: String?,
    val homeTeam: String?,
    val awayTeam: String?,
    val homeScore: String?,
    val awayScore: String?,
    val expiredDate: Date?
)