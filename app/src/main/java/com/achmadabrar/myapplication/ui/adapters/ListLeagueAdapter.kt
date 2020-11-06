package com.achmadabrar.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.achmadabrar.myapplication.ui.viewholders.ListLeagueViewHolder

class ListLeagueAdapter(
    val leagueUi: List<LeagueUiModel>,
    val listener: ListLeagueViewHolder.Listener? = null
): RecyclerView.Adapter<ListLeagueViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListLeagueViewHolder {
        return ListLeagueViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_league, parent, false))
    }

    override fun onBindViewHolder(holder: ListLeagueViewHolder, position: Int) {
        leagueUi[position].let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
       return leagueUi.size
    }
}