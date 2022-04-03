package org.inu.jikbit.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import org.inu.jikbit.presentation.ui.BlankFragment
import org.inu.jikbit.presentation.ui.account.AccountFragment
import org.inu.jikbit.presentation.ui.account.AccountViewModel
import org.inu.jikbit.presentation.ui.market.MarketFragment
//import org.inu.jikbit.presentation.ui.market.MarketFragment
import org.inu.jikbit.presentation.ui.market.MarketViewModel

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