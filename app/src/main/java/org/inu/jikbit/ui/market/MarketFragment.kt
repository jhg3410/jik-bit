package org.inu.jikbit.ui.market


import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.inu.jikbit.R
import org.inu.jikbit.adapter.MarketAdapter
import org.inu.jikbit.base.BaseFragment
import org.inu.jikbit.databinding.FragmentMarketBinding
import org.inu.jikbit.util.observe

class MarketFragment(val viewModel: MarketViewModel) : BaseFragment<FragmentMarketBinding>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int = R.layout.fragment_market

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel =viewModel
        observe(viewModel.viewEvent){
            it.getContentIfNotHandled()?.let{ event ->
                when (event){
                    MarketViewModel.NETWORK_END -> {
                        binding.pullToRefresh.visibility= VISIBLE
                        binding.loading.visibility = INVISIBLE
                    }
                }
            }
        }
    }
    override fun afterDataBinding() {
        showMarkets()
        binding.pullToRefresh.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        showMarkets()
        binding.pullToRefresh.isRefreshing = false
        binding.marketEditText.text.clear()
    }

    private fun showMarkets(){
        viewModel.getMarkets()
        binding.marketRecyclerView.adapter = MarketAdapter(viewModel)
    }
}