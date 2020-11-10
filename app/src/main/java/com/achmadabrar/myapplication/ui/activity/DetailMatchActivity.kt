package com.achmadabrar.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.data.models.Event
import com.achmadabrar.myapplication.ui.viewmodel.DetailMatchViewModel
import kotlinx.android.synthetic.main.activity_detail_match.*
import javax.inject.Inject

class DetailMatchActivity : BaseActivity() {

    @Inject lateinit var viewModel: DetailMatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailMatchViewModel::class.java)

        val data = intent.getParcelableExtra<Event>("event")
        if (data != null) {
            tv_venue.text = data.venue
            tv_away.text = data.awayTeam
            tv_home.text = data.homeTeam
            tv_score_away.text = data.awayScore
            tv_score_home.text = data.homeScore
            viewModel.loadDetailMatch(data.id)
        }

        viewModel.detailMatchLiveData.observe(this, Observer {
            it
        })
    }
}