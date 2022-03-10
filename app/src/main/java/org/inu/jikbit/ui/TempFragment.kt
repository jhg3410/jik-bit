package org.inu.jikbit.ui

import org.inu.jikbit.R
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentTempBinding

class TempFragment(val viewModel: MainViewModel) : BaseFragment<FragmentTempBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_temp

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel =viewModel
    }
}