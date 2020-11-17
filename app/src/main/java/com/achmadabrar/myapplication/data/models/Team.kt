package com.achmadabrar.myapplication.data.models

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("idTeam")
    val id: String,
    @SerializedName("strTeam")
    val team: String,
    @SerializedName("strTeamBadge")
    val logo: String
)