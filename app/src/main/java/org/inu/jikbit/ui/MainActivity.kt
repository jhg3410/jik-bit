package org.inu.jikbit.ui

import androidx.activity.viewModels
import org.inu.jikbit.R
import org.inu.jikbit.base.BaseActivity
import org.inu.jikbit.databinding.ActivityMainBinding
import org.inu.jikbit.util.observe

class MainActivity() : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()


    override fun dataBinding() {
        binding.mainViewModel = viewModel
    }

    override fun afterDataBinding() {
        observe(viewModel.viewEvent){
            it.getContentIfNotHandled()?.let{ event ->
                when(event){
                    MainViewModel.EVENT_GET_ACCOUNTS ->{
                        viewModel.getAccounts()
                    }
                }
            }
        }
    }
}