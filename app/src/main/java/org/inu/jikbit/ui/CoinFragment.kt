package org.inu.jikbit.ui

import org.inu.jikbit.R
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentCoinBinding

class CoinFragment(val viewModel: MainViewModel) : BaseFragment<FragmentCoinBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_coin

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel =viewModel
    }
}