package com.achmadabrar.myapplication.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.data.models.FavModel
import com.achmadabrar.myapplication.ui.viewholders.FavViewHolder

class FavAdapter(
    val listFav: List<FavModel>,
    val listener: FavViewHolder.Listener?
): RecyclerView.Adapter<FavViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        return FavViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_fav_match, parent, false))
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        listFav[position].let {
            holder.bind(it, listener)
        }
    }

    override fun getItemCount(): Int {
        return listFav.size
    }
}