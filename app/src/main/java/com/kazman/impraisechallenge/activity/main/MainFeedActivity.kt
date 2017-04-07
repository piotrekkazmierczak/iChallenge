package com.kazman.impraisechallenge.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Fade
import android.view.Menu
import android.view.MenuItem
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.activity.BaseActivity
import com.kazman.impraisechallenge.databinding.AcitivtyMainFeedBinding

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class MainFeedActivity : BaseActivity<MainFeedViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainFeedViewModel()
        val binding = DataBindingUtil.setContentView<AcitivtyMainFeedBinding>(this, R.layout.acitivty_main_feed)
        //setUpWindowAnimations()
        binding.viewModel = viewModel
        setToolbar(binding.toolbarLayout.toolbar)
        viewModel?.onCreate()
    }

    private fun setUpWindowAnimations() {
        val fade = Fade(2)
        fade.duration = 3000
        window.exitTransition = fade
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_reset) {
            viewModel?.reset()
        }
        return super.onOptionsItemSelected(item)
    }

}
