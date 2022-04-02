package org.inu.jikbit.presentation.ui.account

import android.os.Handler
import android.os.Looper
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.madapps.liquid.LiquidRefreshLayout
import org.inu.jikbit.R
import org.inu.jikbit.presentation.adapter.AccountAdapter
import org.inu.jikbit.global.base.BaseFragment
import org.inu.jikbit.databinding.FragmentAccountBinding
import org.inu.jikbit.global.util.observe

class AccountFragment(val viewModel : AccountViewModel) : BaseFragment<FragmentAccountBinding>(), LiquidRefreshLayout.OnRefreshListener {

    override val layoutResourceId: Int = R.layout.fragment_account

    override fun dataBinding() {
        super.dataBinding()
        binding.viewModel = viewModel
        observe(viewModel.viewEvent) {
            it.getContentIfNotHandled()?.let { event ->
                when (event) {
                    AccountViewModel.NETWORK_END -> {
                        hideLoading()
                    }
                    AccountViewModel.ANI_BUTTON_CLICK -> {
                        animRecyclerView()
                    }
                }
            }
        }
    }

    override fun afterDataBinding() {
        showAccounts()
        binding.pullToRefresh.setOnRefreshListener(this)
    }

//    override fun onRefresh() {
//        showAccounts()
//        binding.pullToRefresh.isRefreshing = false
//    }

    override fun completeRefresh() {
    }

    override fun refreshing() {
        Handler(Looper.getMainLooper()).postDelayed({
            viewModel.getAccounts()
            binding.accountRecyclerView.adapter = AccountAdapter(viewModel)
            binding.pullToRefresh.finishRefreshing()
        },2000)
    }

    private fun showAccounts(){
        showLoading()
        viewModel.getAccounts()
        binding.accountRecyclerView.adapter = AccountAdapter(viewModel)
    }

    private fun showLoading(){
        binding.pullToRefresh.visibility= INVISIBLE
        binding.loading.visibility = VISIBLE
    }

    private fun hideLoading(){
        binding.pullToRefresh.visibility= VISIBLE
        binding.loading.visibility = INVISIBLE
    }

    private fun animRecyclerView(){
        val animShow: Animation = AnimationUtils.loadAnimation(this.context,R.anim.translate_show)
        val animHide: Animation = AnimationUtils.loadAnimation(this.context,R.anim.translate_hide)
        binding.pullToRefresh.run{
            visibility = if (viewModel.aniState.value!!)     {
                startAnimation(animHide)
                INVISIBLE
            } else {
                startAnimation(animShow)
                VISIBLE
            }
        }
    }
}