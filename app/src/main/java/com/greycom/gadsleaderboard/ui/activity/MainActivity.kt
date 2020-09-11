package com.greycom.gadsleaderboard.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayout
import com.greycom.gadsleaderboard.R
import com.greycom.gadsleaderboard.repo.MainRepo
import com.greycom.gadsleaderboard.repo.MainViewModel
import com.greycom.gadsleaderboard.repo.MainViewModelFactory
import com.greycom.gadsleaderboard.ui.fragments.hours.TopLearnersFragment
import com.greycom.gadsleaderboard.ui.fragments.iq.IQFragment
import com.jaeger.library.StatusBarUtil
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : FragmentActivity() {
    private lateinit var pagerAdapter: DemoAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        StatusBarUtil.setTransparent(this)

        initViewModel()
        main_btn_submit.setOnClickListener {
            startActivity(Intent(this, SubmissionActivity::class.java))
        }
        pagerAdapter = DemoAdapter(supportFragmentManager, lifecycle)
        main_pager.adapter = pagerAdapter
        main_pager.beginFakeDrag()


        main_pager.fakeDragBy(-10f)
        main_pager.endFakeDrag()
        initTabListener()


    }

    private fun initTabListener() {
        main_tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                main_pager.currentItem = p0?.position!!
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                main_pager.currentItem = p0?.position!!
            }

        })
    }

    inner class DemoAdapter(val fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle) {
        override fun getItemCount() = 2

        override fun createFragment(position: Int): Fragment {
            if (position == 0) {
                return TopLearnersFragment()
            } else {
                return IQFragment()
            }
        }

    }

    override fun onBackPressed() {
        if (main_pager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            main_pager.currentItem = main_pager.currentItem - 1
        }
    }

    private fun initViewModel() {
        val mainFactory = MainViewModelFactory(application, MainRepo)
        viewModel =
            ViewModelProvider(viewModelStore, mainFactory).get(MainViewModel::class.java)
    }
}