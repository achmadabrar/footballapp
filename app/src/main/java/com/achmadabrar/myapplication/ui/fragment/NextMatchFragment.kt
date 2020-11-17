package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.data.networks.NetworkState
import com.achmadabrar.myapplication.ui.adapters.MatchAdapter
import com.achmadabrar.myapplication.ui.viewholders.MatchViewHolder
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_next_match.*
import javax.inject.Inject


/**
 * Abrar
 */
class NextMatchFragment : BaseFragment(), MatchViewHolder.Listener {

    @Inject
    lateinit var viewModel: MatchViewModel

    lateinit var adapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MatchViewModel::class.java)

        viewModel.nextMatchLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter = MatchAdapter(it, this)
                loadRecycler()
            }
        })

        viewModel.networkStatusLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status.equals(NetworkState.Status.RUNNING)) {
                not_found.visibility = View.GONE
                rv_next_match.visibility = View.GONE
                please_wait.visibility = View.VISIBLE
                error.visibility = View.GONE
            } else if (it.status.equals(NetworkState.Status.SUCCESS)) {
                not_found.visibility = View.GONE
                rv_next_match.visibility = View.VISIBLE
                please_wait.visibility = View.GONE
                error.visibility = View.GONE
            } else if (it.status.equals(NetworkState.Status.EMPTY)) {
                not_found.visibility = View.VISIBLE
                rv_next_match.visibility = View.GONE
                please_wait.visibility = View.GONE
                error.visibility = View.GONE
            } else {
                Toast.makeText(requireContext(), "yahh : ${it.msg}", Toast.LENGTH_SHORT).show()
                not_found.visibility = View.GONE
                rv_next_match.visibility = View.GONE
                please_wait.visibility = View.GONE
                error.visibility = View.VISIBLE
            }
        })

    }

    fun loadRecycler() {
        not_found.visibility = View.GONE
        rv_next_match.visibility = View.VISIBLE
        rv_next_match.adapter = adapter
        rv_next_match.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickEvent(event: Event?) {
        viewModel.loadDetailMatch(event?.id!!)
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.frame_layout_to_detail, DetailMatchFragment())
        transaction?.commit()
    }

}