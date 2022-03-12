package org.inu.jikbit.ui

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.inu.jikbit.R
import org.inu.jikbit.adapter.MarketAdapter
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentMarketBinding

class MarketFragment(val viewModel: MainViewModel) : BaseFragment<FragmentMarketBinding>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int = R.layout.fragment_market

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel =viewModel
    }
    override fun afterDataBinding() {
        showMarkets()
        binding.pullToRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        showMarkets()
        binding.pullToRefresh.isRefreshing = false
    }

    private fun showMarkets(){
        viewModel.getMarkets()
        binding.marketRecyclerView.adapter = MarketAdapter(viewModel)
    }
}