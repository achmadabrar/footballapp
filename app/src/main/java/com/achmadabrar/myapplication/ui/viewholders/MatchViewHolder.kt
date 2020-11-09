package com.achmadabrar.myapplication.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.EventResponse
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_match.view.*

class MatchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(eventNext: Event?, eventPast: EventResponse) {
        with(itemView) {
            if (eventNext != null) {
                Glide.with(this)
                    .load(eventNext.imageMatch)
                    .centerCrop()
                    .into(iv_logo_home)

                Glide.with(this)
                    .load(eventNext.imageMatch)
                    .centerCrop()
                    .into(iv_logo_away)
            }
        }
    }
}