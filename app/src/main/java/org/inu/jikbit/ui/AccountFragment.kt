package org.inu.jikbit.ui

import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
                    AccountViewModel.ANI_BUTTON_CLICK -> {
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