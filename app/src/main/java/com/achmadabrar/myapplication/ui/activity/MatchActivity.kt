package com.achmadabrar.myapplication.ui.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.ui.fragment.MatchFragment
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import javax.inject.Inject

class MatchActivity : BaseActivity() {

    @Inject
    lateinit var viewModel: MatchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MatchViewModel::class.java)
        val data = intent.getLongExtra("idLeague", 0)
        viewModel.loadNextMatch(data)
        viewModel.loadPrevMatch(data)
        val fragment = MatchFragment.newInstance(data)
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_detail_match, fragment).commit()
    }
}