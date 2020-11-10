package com.achmadabrar.myapplication.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.ui.activity.MatchActivity
import com.achmadabrar.myapplication.ui.viewmodel.ListLeagueViewModel
import kotlinx.android.synthetic.main.fragment_option.*
import javax.inject.Inject


/**
 * Abrar
 */
class OptionFragment : BaseFragment() {

    @Inject lateinit var viewModel: ListLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ListLeagueViewModel::class.java)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_option, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.leagueSelectedLiveData.observe(viewLifecycleOwner, Observer {
            clickMatch(it.id)
        })
        containerDetail.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.frameLayout, DetailLeagueFragment())
            transaction?.commit()
        }

    }

    fun clickMatch(idLeague: Long?) {
        containerMatch.setOnClickListener {
            val intent = Intent(requireContext(), MatchActivity::class.java)
            intent.putExtra("idLeague", idLeague)
            startActivity(intent)
        }
    }

}