package com.achmadabrar.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.achmadabrar.myapplication.R
import com.achmadabrar.myapplication.core.base.BaseActivity
import com.achmadabrar.myapplication.ui.fragment.ListLeagueFragment
import com.achmadabrar.myapplication.ui.fragment.NextMatchFragment
import com.achmadabrar.myapplication.ui.fragment.PreviousMatchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, ListLeagueFragment())
        transaction.commit()

        //bottom_nav.setOnNavigationItemSelectedListener(mOnNavigationSelectedListener)
    }

    val mOnNavigationSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId) {
            R.id.navigation_league -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, ListLeagueFragment())
                transaction.commit()
            }
            R.id.navigation_next_match -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, NextMatchFragment())
                transaction.commit()
            }
            R.id.navigation_prev_match -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.frameLayout, PreviousMatchFragment())
                transaction.commit()
            }
        }
        true
    }

    override fun onBackPressed() {
        if (supportFragmentManager.fragments.last().tag.equals("listLeagueFragmentTag")) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }


}