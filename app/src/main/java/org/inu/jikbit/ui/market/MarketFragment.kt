package org.inu.jikbit.ui.market


import android.annotation.SuppressLint
import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
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
                        hideLoading()
                    }
                }
            }
        }
    }

    override fun afterDataBinding() {
        showMarkets()
        binding.pullToRefresh.setOnRefreshListener(this)
        editTextFocusAndKeyboard()
    }

    override fun onRefresh() {
        showMarkets()
        binding.pullToRefresh.isRefreshing = false
        binding.marketEditText.text.clear()
    }

    private fun showMarkets(){
        showLoading()
        viewModel.getMarkets()
        binding.marketRecyclerView.adapter = MarketAdapter(viewModel)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun editTextFocusAndKeyboard(){
        binding.marketRecyclerView.setOnTouchListener { _, _ ->
            binding.marketEditText.clearFocus()
            hideKeyboard()
            false
        }
    }
    private fun showLoading(){
        binding.pullToRefresh.visibility= INVISIBLE
        binding.loading.visibility = VISIBLE
    }

    private fun hideLoading(){
        binding.pullToRefresh.visibility= VISIBLE
        binding.loading.visibility = INVISIBLE
    }

    private fun hideKeyboard(){
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.marketEditText.windowToken,0)
    }
}