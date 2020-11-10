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

class DetailMatchViewModel @Inject constructor(
    val scheduleApi: FootbalScheduleApi
): BaseViewModel<DetailMatchViewModel>() {

    val networkStatusLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    var detailMatchLiveData: MutableLiveData<List<Event>> = MutableLiveData()

    fun loadDetailMatch(idEvent: Long) {
        networkStatusLiveData.postValue(NetworkState.LOADING)
        disposables.add(
            getDetailMatch(idEvent).subscribe({
                networkStatusLiveData.postValue(NetworkState.LOADED)
                detailMatchLiveData.postValue(it)
            }, {
                Log.d("error", "${it.localizedMessage}")
                networkStatusLiveData.postValue(NetworkState.FAILED)
            })
        )
    }

    private fun getDetailMatch(idEvent: Long): Single<List<Event>> {
        return scheduleApi.getDetailMatch(idEvent).map { event ->
            event.events.map {
                Event(id = it.id, matchTitle = it.matchTitle, date = it.date, time = it.time, imageMatch = it.imageMatch, venue = it.venue, homeTeam = it.homeTeam, awayTeam = it.awayTeam, homeScore = it.homeScore, awayScore = it.awayScore, statusMatch = it.statusMatch)
            }
        }
    }
}