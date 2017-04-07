package com.kazman.impraisechallenge

import paperparcel.PaperParcel
import paperparcel.PaperParcelable
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */

@PaperParcel
data class Interaction(val id: Int, val date: String): PaperParcelable {

    // lazy property failed here because of Moshi serialization
    fun getTimeInMillis(): Long {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        val date = simpleDateFormat.parse(date)
        return date.time
    }

    companion object {
        @JvmField val CREATOR = PaperParcelInteraction.CREATOR
    }
}
