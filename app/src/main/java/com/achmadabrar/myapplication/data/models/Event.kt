package com.achmadabrar.myapplication.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Event (
    @SerializedName("idEvent")
    val id: Long,
    @SerializedName("strEvent")
    val matchTitle: String,
    @SerializedName("dateEvent")
    val date: Date,
    @SerializedName("strTime")
    val time: String,
    @SerializedName("strThumb")
    val imageMatch: String?,
    @SerializedName("strVenue")
    val venue: String,
    @SerializedName("strHomeTeam")
    val homeTeam: String,
    @SerializedName("strAwayTeam")
    val awayTeam: String,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("strStatus")
    val statusMatch: String
): Parcelable