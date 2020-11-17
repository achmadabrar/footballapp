package com.achmadabrar.myapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.database.FavEventDao
import com.achmadabrar.myapplication.data.database.NextEventDao
import com.achmadabrar.myapplication.data.database.PrevEventDao
import com.achmadabrar.myapplication.data.database.table.NextEvent
import com.achmadabrar.myapplication.data.database.table.PrevEvent
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.FavModel
import com.achmadabrar.myapplication.data.models.Team
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import com.achmadabrar.myapplication.data.networks.NetworkState
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

private const val ONE_HOUR_CACHE = 3600 * 1000
class MatchViewModel @Inject constructor(
    private val scheduleApi: FootbalScheduleApi,
    private val nextEventDao: NextEventDao,
    private val prevEventDao: PrevEventDao,
    private val favEventDao: FavEventDao
): BaseViewModel<MatchViewModel>() {

    var nextMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    var prevMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    val expDate = Date(System.currentTimeMillis() + ONE_HOUR_CACHE)
    val currentDate = Date(System.currentTimeMillis())
    var detailMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    var logoHomeLiveData:MutableLiveData<String> = MutableLiveData()
    var logoAwayLiveData: MutableLiveData<String> = MutableLiveData()
    var favEventLiveData: MutableLiveData<List<FavModel>> = MutableLiveData()

    fun loadNextMatch(idLeague: Long) {
        val cahceNextMatch = nextEventDao.getNextEvent(idLeague)
        if (cahceNextMatch.isNullOrEmpty()) {
            networkStatusLiveData.postValue(NetworkState.LOADING)
            disposables.add(
                getNextMatch(idLeague).subscribe({
                    nextMatchLiveData.postValue(it)
                    val nextEvent = mutableListOf<NextEvent>()
                    it.map {
                        val event = NextEvent(it.id, idLeague, it.matchTitle, it.statusMatch, it.imageMatch, it.venue, it.homeTeam, it.awayTeam, it.homeScore, it.awayScore, expDate)
                        nextEvent.add(event)
                    }
                    nextEventDao.insertNextEvent(nextEvent)
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                }, {
                    Log.d("error", "${it.localizedMessage}")
                    networkStatusLiveData.postValue(NetworkState.FAILED)
                })
            )
        }  else {
            val nextEvent = mutableListOf<Event>()
            cahceNextMatch.forEach {
                val event = Event(
                    id = it.eventId,
                    matchTitle = it.matchTitle,
                    date = null,
                    time = null,
                    imageMatch = it.matchPoster,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = null,
                    idAway = null,
                    homeFormation = null,
                    awayFormation = null,
                    awayGk = null,
                    awayDefense = null,
                    awayMidfield = null,
                    awayForward = null,
                    awaySubs = null,
                    homeGk = null,
                    homeDefense = null,
                    homeMidfield = null,
                    homeForward = null,
                    homeSubs = null,
                    awayGoalDetail = null,
                    homeGoalDetail = null
                )
                nextEvent.add(event)
            }
            nextMatchLiveData.postValue(nextEvent)
            networkStatusLiveData.postValue(NetworkState.LOADED)
        }
    }

    fun loadPrevMatch(idLeague: Long) {
        val prevEventCache = prevEventDao.getPrevMatch(idLeague)
        if (prevEventCache.isNullOrEmpty()) {
            networkStatusLiveData.postValue(NetworkState.LOADING)
            disposables.addAll(
                getPrevMatch(idLeague).subscribe({
                    filterMatch(it, idLeague)
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                }, {
                    Log.d("error", "${it.localizedMessage}")
                    networkStatusLiveData.postValue(NetworkState.FAILED)
                })
            )
        } else {
            val prevEvent = mutableListOf<Event>()
            prevEventCache.forEach {
                val event = Event(
                    id = it.eventId,
                    matchTitle = it.matchTitle,
                    date = null,
                    time = null,
                    imageMatch = null,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = null,
                    idAway = null,
                    homeFormation = null,
                    awayFormation = null,
                    awayGk = null,
                    awayDefense = null,
                    awayMidfield = null,
                    awayForward = null,
                    awaySubs = null,
                    homeGk = null,
                    homeDefense = null,
                    homeMidfield = null,
                    homeForward = null,
                    homeSubs = null,
                    awayGoalDetail = null,
                    homeGoalDetail = null
                )
                prevEvent.add(event)
            }
            prevMatchLiveData.postValue(prevEvent)
           networkStatusLiveData.postValue(NetworkState.LOADED)
        }
    }

    fun loadAfterSearch(query: String, idLeague: Long) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.add(
            searchMatch(query).subscribe({
                networkStatusLiveData.postValue(NetworkState.LOADED)
                filterMatch(it, idLeague)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    fun filterMatch(event: List<Event>, idLeague: Long) {
        val matchFinished = mutableListOf<Event>()
        val matchNotStarted = mutableListOf<Event>()
        val prevDao = mutableListOf<PrevEvent>()
        event.map {
            if (it.statusMatch.equals("Match Finished")) {
                matchFinished.add(it)
                val prevEvent = PrevEvent(it.id!!, idLeague, it.matchTitle, it.statusMatch, it.venue, it.homeTeam, it.awayTeam, it.homeScore, it.awayScore, expDate)
                prevDao.add(prevEvent)
            } else {
                matchNotStarted.add(it)
            }
            prevEventDao.insertPrevMatch(prevDao)
            prevMatchLiveData.postValue(matchFinished)
            nextMatchLiveData.postValue(matchNotStarted)
        }
    }

    private fun getNextMatch(idLeague: Long): Single<List<Event>> {
        return scheduleApi.getNextMatch(idLeague).map { response ->
            response.events.map {
                Event(
                    id = it.id,
                    matchTitle = it.matchTitle,
                    date = it.date,
                    time = it.time,
                    imageMatch = it.imageMatch,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = it.idHome,
                    idAway = it.idAway,
                    homeFormation = it.homeFormation,
                    awayFormation = it.awayFormation,
                    awayGk = it.awayGk,
                    awayDefense = it.awayDefense,
                    awayMidfield = it.awayMidfield,
                    awayForward = it.awayForward,
                    awaySubs = it.awaySubs,
                    homeGk = it.homeGk,
                    homeDefense = it.homeDefense,
                    homeMidfield = it.homeMidfield,
                    homeForward = it.homeForward,
                    homeSubs = it.homeSubs,
                    awayGoalDetail = it.awayGoalDetail,
                    homeGoalDetail = it.homeGoalDetail
                )
            }
        }
    }

    private fun getPrevMatch(idEvent: Long): Single<List<Event>> {
        return scheduleApi.getPastMatch(idEvent).map { event ->
            event.events.map {
                Event(
                    id = it.id,
                    matchTitle = it.matchTitle,
                    date = it.date,
                    time = it.time,
                    imageMatch = it.imageMatch,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = it.idHome,
                    idAway = it.idAway,
                    homeFormation = it.homeFormation,
                    awayFormation = it.awayFormation,
                    awayGk = it.awayGk,
                    awayDefense = it.awayDefense,
                    awayMidfield = it.awayMidfield,
                    awayForward = it.awayForward,
                    awaySubs = it.awaySubs,
                    homeGk = it.homeGk,
                    homeDefense = it.homeDefense,
                    homeMidfield = it.homeMidfield,
                    homeForward = it.homeForward,
                    homeSubs = it.homeSubs,
                    awayGoalDetail = it.awayGoalDetail,
                    homeGoalDetail = it.homeGoalDetail
                )
            }
        }
    }

    private fun searchMatch(query: String): Single<List<Event>> {
        return scheduleApi.searchMatch(query).map {
            if (it.event.isNullOrEmpty()) {networkStatusLiveData.postValue(NetworkState.EMPTY)}
            it.event.map {
                Event(
                    id = it.id,
                    matchTitle = it.matchTitle,
                    date = it.date,
                    time = it.time,
                    imageMatch = it.imageMatch,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = it.idHome,
                    idAway = it.idAway,
                    homeFormation = it.homeFormation,
                    awayFormation = it.awayFormation,
                    awayGk = it.awayGk,
                    awayDefense = it.awayDefense,
                    awayMidfield = it.awayMidfield,
                    awayForward = it.awayForward,
                    awaySubs = it.awaySubs,
                    homeGk = it.homeGk,
                    homeDefense = it.homeDefense,
                    homeMidfield = it.homeMidfield,
                    homeForward = it.homeForward,
                    homeSubs = it.homeSubs,
                    awayGoalDetail = it.awayGoalDetail,
                    homeGoalDetail = it.homeGoalDetail
                )
            }
        }
    }

    //detail

    fun loadDetailMatch(idEvent: Long) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.add(
            getDetailMatch(idEvent)
                .doOnSuccess {
                    it.map {
                        it.idHome?.let { id -> getDetailTeam(id).subscribe({
                            logoHomeLiveData.postValue(it[0].logo)
                        },{
                            Log.d("error", "${it.localizedMessage}")
                        }) }
                        it.idAway?.let { id -> getDetailTeam(id).subscribe({
                            logoAwayLiveData.postValue(it[0].logo)
                        }, {
                            Log.d("error", "${it.localizedMessage}")
                        }) }
                    }
                }
                .subscribe({
                    networkStatusLiveData.postValue(NetworkState.LOADED)
                    detailMatchLiveData.postValue(it)
                }, {
                    Log.d("error", "${it.localizedMessage}")
                    networkStatusLiveData.postValue(NetworkState.FAILED)
                })
        )
    }

    private fun getDetailTeam(idTeam: String): Single<List<Team>> {
        return scheduleApi.getTeam(idTeam).map {
            it.teams.map {
                Team(id = it.id, logo = it.logo, team = it.team)
            }
        }
    }

    private fun getDetailMatch(idEvent: Long): Single<List<Event>> {
        return scheduleApi.getDetailMatch(idEvent).map { event ->
            event.events.map {
                Event(
                    id = it.id,
                    matchTitle = it.matchTitle,
                    date = it.date,
                    time = it.time,
                    imageMatch = it.imageMatch,
                    venue = it.venue,
                    homeTeam = it.homeTeam,
                    awayTeam = it.awayTeam,
                    homeScore = it.homeScore,
                    awayScore = it.awayScore,
                    statusMatch = it.statusMatch,
                    idHome = it.idHome,
                    idAway = it.idAway,
                    homeFormation = it.homeFormation,
                    awayFormation = it.awayFormation,
                    awayGk = it.awayGk,
                    awayDefense = it.awayDefense,
                    awayMidfield = it.awayMidfield,
                    awayForward = it.awayForward,
                    awaySubs = it.awaySubs,
                    homeGk = it.homeGk,
                    homeDefense = it.homeDefense,
                    homeMidfield = it.homeMidfield,
                    homeForward = it.homeForward,
                    homeSubs = it.homeSubs,
                    awayGoalDetail = it.awayGoalDetail,
                    homeGoalDetail = it.homeGoalDetail)
            }
        }
    }

    fun getFav() {
        val listFav = mutableListOf<FavModel>()
        detailMatchLiveData.value?.map {
            val fav = FavModel(it.id, logoHomeLiveData.value, logoAwayLiveData.value, it.homeTeam, it.awayTeam, it.statusMatch!!)
            listFav.add(fav)
        }
        favEventDao.insertFavEvent(listFav)
        val favEvent = favEventDao.getAllFavEvent()
        favEventLiveData.postValue(favEvent)
    }

    fun removeFav(eventId: Long?) {
        favEventDao.deleteFavEvent(eventId)
        val event = favEventDao.getAllFavEvent()
        favEventLiveData.postValue(event)
    }
}