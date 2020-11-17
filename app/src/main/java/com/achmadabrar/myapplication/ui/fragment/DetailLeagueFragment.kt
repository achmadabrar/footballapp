package com.achmadabrar.myapplication.ui.fragment

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.ui.viewmodel.ListLeagueViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_detail_league.*
import javax.inject.Inject

/**
 * Abrar
 */
class DetailLeagueFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: ListLeagueViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_league, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ListLeagueViewModel::class.java)

        viewModel.leagueSelectedLiveData.observe(viewLifecycleOwner, Observer { leagueUiModel ->
            if (!leagueUiModel.logo.isNullOrEmpty()) {
                Glide.with(this)
                    .load(leagueUiModel.logo)
                    .centerCrop()
                    .apply(RequestOptions().override(250, 250))
                    .into(iv_detail_logo)

                tv_league_desc.text = leagueUiModel.desc
            }

            collapsing.title = leagueUiModel.name
        })


        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
        collapsing.setCollapsedTitleTypeface(Typeface.create(collapsing.expandedTitleTypeface, Typeface.BOLD))

        back.setOnClickListener {
            fragmentManager?.popBackStack()
        }

        /*fab.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.frame_layout_detail, FavoriteMatchFragment())
            transaction?.addToBackStack(null)
            transaction?.commit()
        }*/
    }
}