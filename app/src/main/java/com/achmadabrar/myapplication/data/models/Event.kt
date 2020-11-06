package com.achmadabrar.myapplication.data.models

import com.google.gson.annotations.SerializedName
import java.util.*

data class Event (
    @SerializedName("idEvent")
    val id: Long,
    @SerializedName("strEvent")
    val matchTitle: String,
    @SerializedName("dateEvent")
    val date: Date,
    @SerializedName("strTime")
    val time: Date,
    @SerializedName("strThumb")
    val imageMatch: String,
    @SerializedName("strVenue")
    val venue: String
)