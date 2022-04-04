package org.inu.jikbit.presentation.ui.main

import android.view.View
import androidx.core.content.ContextCompat
import org.inu.jikbit.R
import org.inu.jikbit.presentation.adapter.ViewPagerAdapter
import org.inu.jikbit.global.base.BaseActivity
import org.inu.jikbit.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int = R.layout.activity_main


    override fun dataBinding() {
        val content = findViewById<View>(android.R.id.content)
        splash_out = false
        content.viewTreeObserver.addOnPreDrawListener{splash_out}
    }
    override fun afterDataBinding() {
        binding.pager.adapter = ViewPagerAdapter(this)
        binding.pager.isUserInputEnabled = false
        bottom()
//        binding.pager.registerOnPageChangeCallback(
//            object: ViewPager2.OnPageChangeCallback(){
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    bottom()
//                    binding.bottomNavigationView.menu.items(position).isChecked = true
//                }
//            }
//        )
//        binding.bottomNavigationView.setOnItemSelectedListener(this)
//        val ad = supportFragmentManager.binding.pager as NavHostFragment
//        ExpandableBottomBarNavigationUI.setupWithNavController(binding.bottomNavigationView,)
    }

    private fun bottom(){
        binding.bottomNavigationView.onItemSelectedListener = { view, menuItem, Booelan ->
            when(menuItem.id){
                R.id.page_1 -> {
                    binding.pager.currentItem = 0
                    this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
                }
                R.id.page_2 ->  {
                    binding.pager.currentItem = 1
                    this.window.statusBarColor = ContextCompat.getColor(this, R.color.gray40)
                }
            }
        }
    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        return when(item.itemId){
//            R.id.page_1 -> {
//                binding.pager.currentItem = 0
//                true
//            }
//            R.id.page_2 -> {
//                binding.pager.currentItem = 1
//                true
//            }
//            else -> false
//        }
//    }

    companion object {
        var splash_out = false
    }
}