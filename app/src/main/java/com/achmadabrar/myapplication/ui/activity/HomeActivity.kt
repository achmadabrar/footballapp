package com.achmadabrar.myapplication.ui.activity

import android.os.Bundle
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.ui.fragment.ListLeagueFragment

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, ListLeagueFragment())
        transaction.commit()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.last().tag.equals("listLeagueFragmentTag")) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }
}