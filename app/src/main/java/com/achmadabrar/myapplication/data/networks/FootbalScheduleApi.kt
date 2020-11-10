package com.achmadabrar.myapplication.data.networks

import com.achmadabrar.myapplication.data.models.LeagueResponse
import com.achmadabrar.myapplication.data.models.EventMatchResponse
import com.achmadabrar.myapplication.data.models.EventResponse
import com.achmadabrar.myapplication.data.models.EventSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FootbalScheduleApi {

    companion object {
        const val LOOKUP_LEAGUE = "lookupleague.php"
        const val EVENT_NEXT_LEAGUE = "eventsnextleague.php"
        const val EVENT_PAST_LEAGUE = "eventspastleague.php"
        const val LOOKUP_EVENT = "lookupevent.php"
        const val SEARCH_EVENTS = "searchevents.php"
        const val ALL_LEAGUES = "all_leagues.php"
    }

    @GET(ALL_LEAGUES)
    fun getAllLeagues(): Single<LeagueResponse>

    @GET(LOOKUP_LEAGUE)
    fun getDetailLeague(@Query("id") idLeague: Long): Single<LeagueResponse>

    @GET(EVENT_NEXT_LEAGUE)
    fun getNextMatch(@Query("id") idLeague: Long): Single<EventMatchResponse>

    @GET(EVENT_PAST_LEAGUE)
    fun getPastMatch(@Query("id") idEvent: Long): Single<EventMatchResponse>

    @GET(LOOKUP_EVENT)
    fun getDetailMatch(@Query("id") idEvent: Long): Single<EventMatchResponse>

    @GET(SEARCH_EVENTS)
    fun searchMatch(@Query("e") query: String): Single<EventSearchResponse>
}