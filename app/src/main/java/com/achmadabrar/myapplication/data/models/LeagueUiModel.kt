package com.achmadabrar.myapplication.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "league_table")
data class LeagueUiModel (
    @PrimaryKey val id: Long? = null,
    val name: String? = null,
    val logo: String? = null,
    val isLoading: Boolean = true,
    val desc: String? = null,
    val expiredDate: Date? = null
)