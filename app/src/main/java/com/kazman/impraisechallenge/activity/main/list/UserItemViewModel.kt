package com.kazman.impraisechallenge.activity.main.list

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.kazman.impraisechallenge.User
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class UserItemViewModel(user: User, val showTopPanel: Boolean): BaseObservable(), ObservableOnSubscribe<User> {
    var emitter: ObservableEmitter<User>? = null

    override fun subscribe(e: ObservableEmitter<User>?) {
        emitter = e
    }

    val userItemModel = UserItemModel(user)

    @Bindable
    fun isTopPanelVisible() = showTopPanel

    @Bindable
    fun getTopPanelText() = ""

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
