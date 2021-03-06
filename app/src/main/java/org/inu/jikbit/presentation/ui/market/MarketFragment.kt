package org.inu.jikbit.presentation.ui.market


import android.annotation.SuppressLint
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.OvershootInterpolator
import android.view.inputmethod.InputMethodManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter
import org.inu.jikbit.R
import org.inu.jikbit.presentation.adapter.MarketAdapter
import org.inu.jikbit.global.base.BaseFragment
import org.inu.jikbit.databinding.FragmentMarketBinding
import org.inu.jikbit.presentation.ui.main.MainActivity.Companion.splash_out
import org.inu.jikbit.global.util.observe

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
                        splash_out = true
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
//        binding.marketRecyclerView.adapter = MarketAdapter()
        observe(viewModel.marketList){

//            (binding.marketRecyclerView.adapter as MarketAdapter).submitList(it)
            binding.marketRecyclerView.adapter = ScaleInAnimationAdapter(MarketAdapter(viewModel).apply {
                submitList(it)
            }).apply {
                setFirstOnly(false)
                setDuration(1000)
                setInterpolator(OvershootInterpolator())
            }
        }


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