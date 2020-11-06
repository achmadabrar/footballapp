package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.achmadabrar.myapplication.ui.ListLeagueItemDecoration
import com.achmadabrar.myapplication.ui.adapters.ListLeagueAdapter
import com.achmadabrar.myapplication.ui.viewholders.ListLeagueViewHolder
import com.achmadabrar.myapplication.ui.viewmodel.ListLeagueViewModel
import kotlinx.android.synthetic.main.fragment_list_league.*
import javax.inject.Inject


/**
 * Abrar
 */
class ListLeagueFragment : BaseFragment(), ListLeagueViewHolder.Listener {

    @Inject
    lateinit var viewModel: ListLeagueViewModel
    var adapter: ListLeagueAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ListLeagueViewModel::class.java)

        viewModel.listLeagueLiveData.observe(viewLifecycleOwner, Observer {
            if (it[0].isLoading) {
                recycler_list_league.visibility = View.GONE
                shimmer_list_league.visibility = View.VISIBLE
            }
            shimmer_list_league.visibility = View.GONE
            adapter = ListLeagueAdapter(it, this)
            loadRecyclerView()
        })
        (activity as AppCompatActivity).setSupportActionBar(toolbar_list_league)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.list_league)
    }

    fun loadRecyclerView() {
        recycler_list_league.visibility = View.VISIBLE
        recycler_list_league.adapter = adapter
        recycler_list_league.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recycler_list_league.addItemDecoration(ListLeagueItemDecoration())
    }

    override fun onClickItem(leagueUiModel: LeagueUiModel) {
            viewModel.leagueLiveData.postValue(leagueUiModel)
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.frameLayout, DetailLeagueFragment(), "listLeagueFragmentTag")
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
    }
}