package com.kazman.impraisechallenge

import android.text.format.DateUtils
import android.widget.Toast
import com.kazman.impraisechallenge.di.Injector
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */

fun <T> T.toast(message: String?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(Injector.component?.getContext(), message, duration).show()
}

fun <T> T.getString(resId: Int) = Injector.component?.getContext()?.getString(resId)

fun <T> T.millisToDateString(millis: Long) : String {
    val date = Date(millis)
    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
    return simpleDateFormat.format(date)
}

fun Interaction.getRelativeTimeSpanString(): String {
    return DateUtils.getRelativeTimeSpanString(this.getTimeInMillis(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS).toString()
}
