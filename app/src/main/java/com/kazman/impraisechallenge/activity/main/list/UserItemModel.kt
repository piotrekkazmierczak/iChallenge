package com.kazman.impraisechallenge.activity.main.list

import android.text.format.DateUtils
import com.kazman.impraisechallenge.*

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */
class UserItemModel(val user: User) {
    fun getLastFeedbackInfo(): String? {
        if (user.last_interactions.isEmpty())
            return getString(R.string.ich_no_feedback_given)
        else {
            return getString(R.string.ich_last_feedback) + DateUtils.getRelativeTimeSpanString(user.getLastInteraction()?.getTimeInMillis() ?: 0L, System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS);
        }
    }

    fun addNewInteraction() {
        user.last_interactions.add(Interaction((user.getLastInteraction()?.id ?: 0) + 1, millisToDateString(System.currentTimeMillis())))
    }
}
