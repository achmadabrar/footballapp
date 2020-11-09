package com.achmadabrar.myapplication.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.achmadabrar.myapplication.core.base.BaseViewModel
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.EventResponse
import com.achmadabrar.myapplication.data.networks.FootbalScheduleApi
import io.reactivex.Single
import javax.inject.Inject

class MatchViewModel @Inject constructor(
    val scheduleApi: FootbalScheduleApi
): BaseViewModel<MatchViewModel>() {

    var nextMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()
    var prevMatchLiveData: MutableLiveData<EventResponse> = MutableLiveData()
    var detailMatchLiveData: MutableLiveData<EventResponse> = MutableLiveData()
    var searchMatchLiveData: MutableLiveData<EventResponse> = MutableLiveData()

    fun loadNextMatch(idLeague: Long) {
        disposables.add(
            getNextMatch(idLeague).subscribe({
                nextMatchLiveData.postValue(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    fun loadPrevMatch(idEvent: Long) {
        disposables.addAll(
            getPrevMatch(idEvent).subscribe({
                prevMatchLiveData.postValue(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    fun loadDetailMatch(idEvent: Long) {
        disposables.add(
            getDetailMatch(idEvent).subscribe({
                detailMatchLiveData.postValue(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    fun loadAfterSearch(query: String) {
        disposables.add(
            searchMatch(query).subscribe({
                searchMatchLiveData.postValue(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
            })
        )
    }

    private fun getNextMatch(idLeague: Long): Single<List<Event>> {
        return scheduleApi.getNextMatch(idLeague).map { response ->
            response.events.map {
                Event(id = it.id, matchTitle = it.matchTitle, date = it.date, time = it.time, imageMatch = it.imageMatch, venue = it.venue)
            }
        }
    }

    private fun getPrevMatch(idEvent: Long): Single<EventResponse> {
        return scheduleApi.getPastMatch(idEvent).map { event ->
            EventResponse(homeTeam = event.homeTeam, awayTeam = event.awayTeam, homeScore = event.homeScore, awayScore = event.awayScore, date = event.date, time = event.time, venue = event.venue, imageMatch = event.imageMatch)
        }
    }

    private fun getDetailMatch(idEvent: Long): Single<EventResponse> {
        return scheduleApi.getDetailMatch(idEvent).map { event ->
            EventResponse(homeTeam = event.homeTeam, awayTeam = event.awayTeam, homeScore = event.homeScore, awayScore = event.awayScore, date = event.date, time = event.time, venue = event.venue, imageMatch = event.imageMatch)
        }
    }

    private fun searchMatch(query: String): Single<EventResponse> {
        return scheduleApi.searchMatch(query).map { event ->
            EventResponse(homeTeam = event.homeTeam, awayTeam = event.awayTeam, homeScore = event.homeScore, awayScore = event.awayScore, date = event.date, time = event.time, venue = event.venue, imageMatch = event.imageMatch)
        }
    }
}