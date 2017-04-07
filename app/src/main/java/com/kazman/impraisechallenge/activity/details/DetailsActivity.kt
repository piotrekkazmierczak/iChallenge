package com.kazman.impraisechallenge.activity.details

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.transition.Slide
import android.view.Gravity
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.User
import com.kazman.impraisechallenge.activity.BaseActivity
import com.kazman.impraisechallenge.databinding.ActivityDetailsBinding
import com.kazman.impraisechallenge.userParcel


/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class DetailsActivity : BaseActivity<DetailsViewModel>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = intent.getParcelableExtra<User>(userParcel)
        viewModel = DetailsViewModel(user)
        val binding = DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
        setUpWindowAnimations()
        binding.viewModel = viewModel
        viewModel?.onCreate()
        setToolbar(binding.toolbarLayout.toolbar, true, getString(R.string.ich_user_profile))
    }

    private fun setUpWindowAnimations() {
        val slide = Slide(Gravity.LEFT)
        slide.duration = 150
        window.enterTransition = slide
        val slideOut = Slide(Gravity.RIGHT)
        slide.duration = 150
        window.exitTransition = slideOut
    }
}
