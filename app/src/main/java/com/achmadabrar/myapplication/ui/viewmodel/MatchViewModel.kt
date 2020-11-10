package com.achmadabrar.myapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.EventResponse
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import com.achmadabrar.myapplication.data.networks.NetworkState
import io.reactivex.Single
import javax.inject.Inject

class MatchViewModel @Inject constructor(
    val scheduleApi: FootbalScheduleApi
): BaseViewModel<MatchViewModel>() {

    var nextMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    var prevMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()

    fun loadNextMatch(idLeague: Long) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.add(
            getNextMatch(idLeague).subscribe({
                nextMatchLiveData.postValue(it)
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }, {
                Log.d("error", "${it.localizedMessage}")
                networkStatusLiveData.postValue(NetworkState.FAILED)
            })
        )
    }

    fun loadPrevMatch(idEvent: Long) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.addAll(
            getPrevMatch(idEvent).subscribe({
                filterMatch(it)
                networkStatusLiveData.postValue(NetworkState.LOADED)
            }, {
                Log.d("error", "${it.localizedMessage}")
                networkStatusLiveData.postValue(NetworkState.FAILED)
            })
        )
    }

    fun loadAfterSearch(query: String) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.add(
            searchMatch(query).subscribe({
                networkStatusLiveData.postValue(NetworkState.LOADED)
                filterMatch(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    fun filterMatch(event: List<Event>) {
        val matchFinished = mutableListOf<Event>()
        val matchNotStarted = mutableListOf<Event>()
        event.map {
            if (it.statusMatch.equals("Match Finished")) {
                matchFinished.add(it)
            } else {
                matchNotStarted.add(it)
            }
            prevMatchLiveData.postValue(matchFinished)
            nextMatchLiveData.postValue(matchNotStarted)
        }
    }

    private fun getNextMatch(idLeague: Long): Single<List<Event>> {
        return scheduleApi.getNextMatch(idLeague).map { response ->
            response.events.map {
                Event(id = it.id, matchTitle = it.matchTitle, date = it.date, time = it.time, imageMatch = it.imageMatch, venue = it.venue, homeTeam = it.homeTeam, awayTeam = it.awayTeam, homeScore = it.homeScore, awayScore = it.awayScore, statusMatch = it.statusMatch)
            }
        }
    }

    private fun getPrevMatch(idEvent: Long): Single<List<Event>> {
        return scheduleApi.getPastMatch(idEvent).map { event ->
            event.events.map {
                Event(id = it.id, matchTitle = it.matchTitle, date = it.date, time = it.time, imageMatch = it.imageMatch, venue = it.venue, homeTeam = it.homeTeam, awayTeam = it.awayTeam, homeScore = it.homeScore, awayScore = it.awayScore, statusMatch = it.statusMatch)
            }
        }
    }

    private fun searchMatch(query: String): Single<List<Event>> {
        return scheduleApi.searchMatch(query).map {
            if (it.event.isNullOrEmpty()) {networkStatusLiveData.postValue(NetworkState.EMPTY)}
            it.event.map {
                Event(id = it.id, matchTitle = it.matchTitle, date = it.date, time = it.time, imageMatch = it.imageMatch, venue = it.venue, homeTeam = it.homeTeam, awayTeam = it.awayTeam, homeScore = it.homeScore, awayScore = it.awayScore, statusMatch = it.statusMatch)
            }
        }
    }
}