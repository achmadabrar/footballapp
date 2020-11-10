package com.achmadabrar.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.EventResponse
import com.achmadabrar.myapplication.ui.viewholders.MatchViewHolder

class MatchAdapter(
    val event: List<Event>,
    val listener: MatchViewHolder.Listener?
): RecyclerView.Adapter<MatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false))
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        event[position].let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return event.size
    }

}