package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.data.models.FavModel
import com.achmadabrar.myapplication.ui.adapters.FavAdapter
import com.achmadabrar.myapplication.ui.viewholders.FavViewHolder
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_favorite_match.*
import javax.inject.Inject

/**
 * Abrar
 */
class FavoriteMatchFragment : BaseFragment(), FavViewHolder.Listener {

    @Inject lateinit var viewModel: MatchViewModel
    lateinit var adapter: FavAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(MatchViewModel::class.java)

        viewModel.favEventLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNullOrEmpty()) {
                tv_empty.visibility = View.VISIBLE
                recycler_fav_match.visibility = View.GONE
            } else {
                adapter = FavAdapter(it, this)
                loadRecycler()
                tv_empty.visibility = View.GONE
            }

        })
    }

    fun loadRecycler() {
        recycler_fav_match.adapter = adapter
        recycler_fav_match.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickFavEvent(event: FavModel?) {
        viewModel.removeFav(event?.eventId)
    }

}