package com.achmadabrar.myapplication.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.data.models.FavModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_fav_match.view.*

class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: FavModel?, listener: Listener?) {
        with(itemView) {
            tv_status_match.text = event?.statusMatch
            tv_home_club.text = event?.homeTeam
            tv_away_club.text = event?.awayTeam
            Glide.with(this)
                .load(event?.homeLogo)
                .centerInside()
                .apply(RequestOptions().override(250, 250))
                .into(iv_logo_home)

            Glide.with(this)
                .load(event?.awayLogo)
                .centerInside()
                .apply(RequestOptions().override(250, 250))
                .into(iv_logo_away)

            iv_remove_fav.setOnClickListener {
                listener?.onClickFavEvent(event)
            }
        }
    }

    interface Listener {
        fun onClickFavEvent(event: FavModel?)
    }
}