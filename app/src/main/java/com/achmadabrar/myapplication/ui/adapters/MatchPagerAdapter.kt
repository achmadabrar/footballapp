package com.achmadabrar.myapplication.ui.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.achmadabrar.myapplication.ui.fragment.NextMatchFragment
import com.achmadabrar.myapplication.ui.fragment.PreviousMatchFragment

class MatchPagerAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

    private val pages = listOf(
        NextMatchFragment(),
        PreviousMatchFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position] as Fragment
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position) {
            0 -> "Pertandingan berikutnya"
            else -> "Pertandingan yang lalu"

        }
    }
}