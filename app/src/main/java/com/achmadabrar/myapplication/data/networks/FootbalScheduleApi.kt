package com.achmadabrar.myapplication.data.networks

import com.achmadabrar.myapplication.data.models.LeagueResponse
import com.achmadabrar.myapplication.data.models.NextEventResponse
import com.achmadabrar.myapplication.data.models.EventResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FootbalScheduleApi {

    companion object {
        const val LOOKUP_LEAGUE = "lookupleague.php"
        const val EVENT_NEXT_LEAGUE = "eventsnextleague.php"
        const val EVENT_PAST_LEAGUE = "eventpastleague.php"
        const val LOOKUP_EVENT = "lookupevent.php"
        const val SEARCH_EVENTS = "searchevents.php"
        const val ALL_LEAGUES = "all_leagues.php"
    }

    @GET(ALL_LEAGUES)
    fun getAllLeagues(): Single<LeagueResponse>

    @GET(LOOKUP_LEAGUE)
    fun getDetailLeague(@Query("id") idLeague: Long): Single<LeagueResponse>

    @GET(EVENT_NEXT_LEAGUE)
    fun getNextMatch(@Query("id") idLeague: Long): NextEventResponse

    @GET(EVENT_PAST_LEAGUE)
    fun getPastMatch(@Query("id") idEvent: Long): EventResponse

    @GET(LOOKUP_EVENT)
    fun getDetailMatch(@Query("id") idEvent: Long): EventResponse

    @GET(SEARCH_EVENTS)
    fun searchMatch(@Query("e") query: String): EventResponse
}