package org.inu.jikbit.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.inu.jikbit.ui.AccountFragment
import org.inu.jikbit.ui.MainViewModel
import org.inu.jikbit.ui.CoinFragment

class ViewPagerAdapter(fa: FragmentActivity, val viewModel: MainViewModel):FragmentStateAdapter(fa){

    companion object {
        const val NUMBER_OF_PAGE = 2
    }

    override fun getItemCount(): Int = NUMBER_OF_PAGE

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> CoinFragment(viewModel = viewModel)
            1 -> AccountFragment(viewModel = viewModel)
            else -> throw IllegalAccessException("????")
        }
    }
}