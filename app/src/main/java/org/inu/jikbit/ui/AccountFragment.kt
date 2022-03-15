package org.inu.jikbit.ui

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.inu.jikbit.R
import org.inu.jikbit.adapter.AccountAdapter
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentAccountBinding
import org.inu.jikbit.util.observe

class AccountFragment(val viewModel : AccountViewModel) : BaseFragment<FragmentAccountBinding>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int = R.layout.fragment_account

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel = viewModel
        observe(viewModel.viewEvent){
            it.getContentIfNotHandled()?.let{ event ->
                when (event){
                    AccountViewModel.NETWORK_END -> {
                        binding.pullToRefresh.visibility= VISIBLE
                        binding.loading.visibility = INVISIBLE
                    }
                }
            }
        }
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