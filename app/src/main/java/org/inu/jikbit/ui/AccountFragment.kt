package org.inu.jikbit.ui

import org.inu.jikbit.R
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentAccountBinding

class AccountFragment(val viewModel : MainViewModel) : BaseFragment<FragmentAccountBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_account

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel = viewModel
    }
}