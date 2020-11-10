package com.achmadabrar.myapplication.ui.activity

import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.ui.adapters.MatchPagerAdapter
import com.achmadabrar.myapplication.ui.viewmodel.MatchViewModel
import kotlinx.android.synthetic.main.activity_match.*
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
        view_pager.adapter = MatchPagerAdapter(supportFragmentManager)

        search_view.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.loadAfterSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }

        })
    }
}