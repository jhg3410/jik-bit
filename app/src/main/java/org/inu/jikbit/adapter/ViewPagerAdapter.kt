package org.inu.jikbit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.inu.jikbit.ui.*

class ViewPagerAdapter(fa: FragmentActivity):FragmentStateAdapter(fa){

    companion object {
        const val NUMBER_OF_PAGE = 2
    }

    override fun getItemCount(): Int = NUMBER_OF_PAGE

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MarketFragment(viewModel = MarketViewModel())
            1 -> AccountFragment(viewModel = AccountViewModel())
            else -> throw IllegalAccessException("????")
        }
    }
}