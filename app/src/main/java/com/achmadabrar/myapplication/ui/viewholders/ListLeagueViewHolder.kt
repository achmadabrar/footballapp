package com.achmadabrar.myapplication.ui.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_league.view.*

class ListLeagueViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(leagueUiModel: LeagueUiModel, listener: Listener?) {
        with(itemView) {
            Glide.with(this)
                .load(leagueUiModel.logo)
                .centerInside()
                .apply(RequestOptions().override(250, 250))
                .into(iv_league_logo)
            tv_league_name.text = leagueUiModel.name

            listener.let {
                itemView.setOnClickListener { _ ->
                    it?.onClickItem(leagueUiModel)
                }
            }

        }
    }

    interface Listener {
        fun onClickItem(leagueUiModel: LeagueUiModel)
    }
}