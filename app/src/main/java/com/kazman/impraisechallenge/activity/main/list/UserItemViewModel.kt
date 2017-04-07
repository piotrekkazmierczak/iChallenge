package com.kazman.impraisechallenge.activity.main.list

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.User
import com.kazman.impraisechallenge.getString
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class UserItemViewModel(user: User, val showTopPanel: Boolean, val secondList: Boolean) : BaseObservable(), ObservableOnSubscribe<User> {
    var emitter: ObservableEmitter<User>? = null

    override fun subscribe(e: ObservableEmitter<User>?) {
        emitter = e
    }

    val userItemModel = UserItemModel(user)

    @Bindable
    fun isTopPanelVisible() = showTopPanel

    @Bindable
    fun getTopPanelText(): String {
        if (secondList) {
            return getString(R.string.ich_give_feedback)!!
        } else {
            return getString(R.string.ich_gave_feedback_recently)!!
        }
    }

    @Bindable
    fun getAvatarUrl() = userItemModel.user.avatar

    @Bindable
    fun getUserName() = userItemModel.user.name

    @Bindable
    fun getLastInteraction() = userItemModel.getLastFeedbackInfo()

    fun onFeedbackClicked(): () -> Unit = {
        userItemModel.addNewInteraction()
        emitter?.onNext(userItemModel.user)
    }
}
