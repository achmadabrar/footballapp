package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.ui.adapters.MatchPagerAdapter
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.fragment_match.*
import javax.inject.Inject


/**
 * Abrar
 */
class MatchFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MatchViewModel

    companion object {
        const val ID_LEAGUE = "idLeague"
        fun newInstance(idLeague: Long): MatchFragment {
            val fragment = MatchFragment()
            val bundle = Bundle().apply {
                putLong(ID_LEAGUE, idLeague)
            }
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager.adapter = MatchPagerAdapter(childFragmentManager)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MatchViewModel::class.java)

        val idLeague = arguments?.getLong(ID_LEAGUE)

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.loadAfterSearch(query, idLeague!!)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }


}