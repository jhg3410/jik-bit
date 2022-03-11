package org.inu.jikbit.ui

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.inu.jikbit.R
import org.inu.jikbit.adapter.AccountAdapter
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentAccountBinding

class AccountFragment(val viewModel : MainViewModel) : BaseFragment<FragmentAccountBinding>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int = R.layout.fragment_account

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel = viewModel
    }

    override fun afterDataBinding() {
        showAccounts()
        binding.pullToRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        showAccounts()
        binding.pullToRefresh.isRefreshing = false
    }

    private fun showAccounts(){
        viewModel.getAccounts()
        binding.accountRecyclerView.adapter = AccountAdapter(viewModel)
    }
}