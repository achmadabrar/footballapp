package com.achmadabrar.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.ui.adapters.MatchPagerAdapter
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        //view_pager.adapter = MatchPagerAdapter(supportFragmentManager)
    }
}