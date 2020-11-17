package com.achmadabrar.myapplication.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "fav_event_table")
data class Event (
    @SerializedName("idEvent")
    @PrimaryKey val id: Long?,
    @SerializedName("strEvent")
    val matchTitle: String?,
    @SerializedName("dateEvent")
    val date: Date?,
    @SerializedName("strTime")
    val time: String?,
    @SerializedName("strThumb")
    val imageMatch: String?,
    @SerializedName("strVenue")
    val venue: String?,
    @SerializedName("strHomeTeam")
    val homeTeam: String?,
    @SerializedName("strAwayTeam")
    val awayTeam: String?,
    @SerializedName("intHomeScore")
    val homeScore: String?,
    @SerializedName("intAwayScore")
    val awayScore: String?,
    @SerializedName("strStatus")
    val statusMatch: String?,
    @SerializedName("idHomeTeam")
    val idHome: String?,
    @SerializedName("idAwayTeam")
    val idAway: String?,
    @SerializedName("strHomeFormation")
    val homeFormation: String?,
    @SerializedName("strHomeLineupGoalkeeper")
    val homeGk: String?,
    @SerializedName("strHomeLineupDefense")
    val homeDefense: String?,
    @SerializedName("strHomeLineupMidfield")
    val homeMidfield: String?,
    @SerializedName("strHomeLineupForward")
    val homeForward: String?,
    @SerializedName("strHomeLineupSubstitutes")
    val homeSubs: String?,
    @SerializedName("strAwayLineupGoalkeeper")
    val awayGk: String?,
    @SerializedName("strAwayLineupDefense")
    val awayDefense: String?,
    @SerializedName("strAwayLineupMidfield")
    val awayMidfield: String?,
    @SerializedName("strAwayLineupForward")
    val awayForward: String?,
    @SerializedName("strAwayLineupSubstitutes")
    val awaySubs: String?,
    @SerializedName("strAwayFormation")
    val awayFormation: String?,
    @SerializedName("strHomeGoalDetails")
    val homeGoalDetail: String?,
    @SerializedName("strAwayGoalDetails")
    val awayGoalDetail: String?
): Parcelable