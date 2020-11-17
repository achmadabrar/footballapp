package com.achmadabrar.myapplication.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseFragment
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_detail_match.*
import javax.inject.Inject

/**
 * Abrar
 */


class DetailMatchFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MatchViewModel

    companion object{
        private const val FINISHED = "Match Finished"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(activity!!, viewModelFactory)
            .get(MatchViewModel::class.java)

        viewModel.logoAwayLiveData.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .centerInside()
                .apply(RequestOptions().override(250, 250))
                .into(iv_logo_away)
        })

        viewModel.logoHomeLiveData.observe(this, Observer {
            Glide.with(this)
                .load(it)
                .centerInside()
                .apply(RequestOptions().override(250, 250))
                .into(iv_logo_home)
        })

        viewModel.detailMatchLiveData.observe(this, Observer {
            it.map { data ->
                tv_venue.text = data.venue
                tv_away.text = data.awayTeam
                tv_home.text = data.homeTeam
                tv_score_away.text = data.awayScore
                tv_score_home.text = data.homeScore
                tv_status_match.text = data.statusMatch
                if (data.statusMatch == FINISHED) {
                    tv_status_match.text = data.statusMatch
                    tv_gk_home.text = resources.getString(R.string.goal_keeper, data.homeGk)
                    tv_gk_away.text = resources.getString(R.string.goal_keeper, data.awayGk)
                    tv_defence_home.text = resources.getString(R.string.defence, data.homeDefense)
                    tv_defence_away.text = resources.getString(R.string.defence, data.awayDefense)
                    tv_mid_away.text = resources.getString(R.string.midfield, data.awayMidfield)
                    tv_mid_home.text = resources.getString(R.string.midfield, data.homeMidfield)
                    tv_forward_away.text = resources.getString(R.string.forward, data.awayForward)
                    tv_forward_home.text = resources.getString(R.string.forward, data.homeForward)
                    tv_subs_away.text = resources.getString(R.string.subs, data.awaySubs)
                    tv_subs_home.text = resources.getString(R.string.subs, data.homeSubs)
                    tv_formation_away.text = data.awayFormation
                    tv_formation_home.text = data.homeFormation
                    tv_goalscorer_title_away.text =
                        resources.getString(R.string.goal_scorer, data.awayGoalDetail)
                    tv_goalscorer_title_home.text =
                        resources.getString(R.string.goal_scorer, data.homeGoalDetail)
                }
            }
        })

        fab.setOnClickListener {
            viewModel.getFav()
            val transaction = fragmentManager?.beginTransaction()
            transaction?.addToBackStack(null)
            transaction?.replace(R.id.frame_layout_to_detail, FavoriteMatchFragment())
            transaction?.commit()
        }
    }

}