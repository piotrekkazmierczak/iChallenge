package com.kazman.impraisechallenge.activity.details

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.User
import com.kazman.impraisechallenge.activity.ViewModel
import com.kazman.impraisechallenge.activity.details.list.InteractionAdapter
import com.kazman.impraisechallenge.getString

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class DetailsViewModel(val user: User) : BaseObservable(), ViewModel {
    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun onPause() {
    }

    override fun onResume() {
    }

    @Bindable
    fun getAvatarUrl() =  user.avatar

    @Bindable
    fun getUserName() = user.name

    @Bindable
    fun getAdapter() = InteractionAdapter().also { it.addItems(user.last_interactions) }

    @Bindable
    fun getFeedbackText() = getString(R.string.ich_feedback_given_to) + user.name

    @Bindable
    fun isEmptyStateVisible() = user.last_interactions.size == 0

}
