package com.kazman.impraisechallenge.activity

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import com.kazman.impraisechallenge.R

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
open class BaseActivity<T : ViewModel>: AppCompatActivity() {
    var viewModel: T? = null

    override fun onPause() {
        super.onPause()
        viewModel?.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.onDestroy()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.onResume()
    }

    fun setToolbar(appToolbar: Toolbar, backButton: Boolean = false, title: String = getString(R.string.ich_main_screen_title)) {
        appToolbar.title = title
        setSupportActionBar(appToolbar)
        if (backButton) {
            appToolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            appToolbar.setNavigationOnClickListener { this@BaseActivity.supportFinishAfterTransition() }
        }
    }
}
