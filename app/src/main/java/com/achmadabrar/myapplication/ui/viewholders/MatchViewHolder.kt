package com.achmadabrar.myapplication.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.models.EventResponse
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_match.view.*

class MatchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    companion object {
        private const val FINISH = "Match Finished"
    }

    fun bind(event: Event?, listener: Listener?) {
        with(itemView) {
            when(event?.statusMatch) {
                FINISH -> {
                    iv_poster_next.visibility = View.GONE
                    tv_venue.visibility = View.VISIBLE
                    tv_venue.text = event.venue
                    tv_home.visibility = View.VISIBLE
                    tv_home.text = event.homeTeam
                    tv_away.visibility = View.VISIBLE
                    tv_away.text = event.awayTeam
                    tv_score_away.visibility = View.VISIBLE
                    tv_score_away.text = event.awayScore
                    tv_score_home.visibility = View.VISIBLE
                    tv_score_home.text = event.homeScore

                    itemView.setOnClickListener {
                        listener?.onClickEvent(event)
                    }
                }
                else -> {
                    Glide.with(this)
                        .load(event?.imageMatch)
                        .centerCrop()
                        .apply(RequestOptions().override(1000, 250))
                        .into(iv_poster_next)

                    iv_poster_next.visibility = View.VISIBLE
                    tv_venue.visibility = View.GONE
                    tv_home.visibility = View.GONE
                    tv_away.visibility = View.GONE
                    tv_score_away.visibility = View.GONE
                    tv_score_home.visibility = View.GONE
                    tv_vs.visibility = View.GONE

                    itemView.setOnClickListener {
                        listener?.onClickEvent(event)
                    }
                }
            }
        }
    }

    interface Listener {
        fun onClickEvent(event: Event?)
    }
}