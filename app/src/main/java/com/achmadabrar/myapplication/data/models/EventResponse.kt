package com.achmadabrar.myapplication.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class EventResponse (
    @SerializedName("strHomeTeam")
    val homeTeam: String?,
    @SerializedName("strAwayTeam")
    val awayTeam: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("dateEvent")
    val date: Date,
    @SerializedName("strTime")
    val time: Date,
    @SerializedName("strVenue")
    val venue: String,
    @SerializedName("strThumb")
    val imageMatch: String
)