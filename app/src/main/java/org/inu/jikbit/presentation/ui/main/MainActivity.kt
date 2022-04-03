package org.inu.jikbit.presentation.ui.main

import android.view.MenuItem
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationBarView
import org.inu.jikbit.R
import org.inu.jikbit.presentation.adapter.ViewPagerAdapter
import org.inu.jikbit.global.base.BaseActivity
import org.inu.jikbit.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(), NavigationBarView.OnItemSelectedListener {
    override val layoutResourceId: Int = R.layout.activity_main
//    private val viewModel: MainViewModel by viewModels()


    override fun dataBinding() {
//        binding.mainViewModel = viewModel
        val content = findViewById<View>(android.R.id.content)
        splash_out = false
        content.viewTreeObserver.addOnPreDrawListener{splash_out}
    }
    override fun afterDataBinding() {
        binding.pager.adapter = ViewPagerAdapter(this)
        binding.pager.isUserInputEnabled = false
        binding.pager.registerOnPageChangeCallback(
            object: ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.bottomNavigationView.menu.getItem(position).isChecked = true
                }
            }
        )
        binding.bottomNavigationView.setOnItemSelectedListener(this)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.page_1 -> {
                binding.pager.currentItem = 0
                true
            }
            R.id.page_2 -> {
                binding.pager.currentItem = 1
                true
            }
            else -> false
        }
    }

    companion object {
        var splash_out = false
    }
}