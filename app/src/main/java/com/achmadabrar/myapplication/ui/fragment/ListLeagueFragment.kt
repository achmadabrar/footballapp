package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.data.models.LeagueUiModel
import com.achmadabrar.myapplication.data.networks.NetworkState
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
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ListLeagueViewModel::class.java)
        viewModel.listLeagueLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter = ListLeagueAdapter(it, this)
                loadRecyclerView()
            }
        })
        viewModel.networkStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status.equals(NetworkState.Status.RUNNING)) {
                shimmer_list_league.visibility = View.VISIBLE
                recycler_list_league.visibility = View.GONE
            } else if (it.status.equals(NetworkState.Status.SUCCESS)) {
                shimmer_list_league.visibility = View.GONE
                recycler_list_league.visibility = View.VISIBLE
            } else  {
                Toast.makeText(requireContext(), "yahh : ${it.msg}", Toast.LENGTH_SHORT).show()
            }
        })

        (activity as AppCompatActivity).setSupportActionBar(toolbar_list_league)
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.list_league)
        return inflater.inflate(R.layout.fragment_list_league, container, false)
    }

    fun loadRecyclerView() {
        recycler_list_league.adapter = adapter
        recycler_list_league.layoutManager =
            GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        recycler_list_league.addItemDecoration(ListLeagueItemDecoration())
    }

    override fun onClickItem(leagueUiModel: LeagueUiModel) {
            viewModel.leagueSelectedLiveData.postValue(leagueUiModel)
            val fragmentTransaction = fragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.frameLayout, OptionFragment(), "listLeagueFragmentTag")
            fragmentTransaction?.addToBackStack(null)
            fragmentTransaction?.commit()
    }
}