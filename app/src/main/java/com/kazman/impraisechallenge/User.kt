package com.kazman.impraisechallenge

import paperparcel.PaperParcel
import paperparcel.PaperParcelable

/**
 * Created by piotrkazmierczak on 05.04.2017.
 */

@PaperParcel
data class User(val id: Int, val name: String, val email: String, val avatar: String?, val last_interactions: MutableList<Interaction>): PaperParcelable {
    companion object {
        @JvmField val CREATOR = PaperParcelUser.CREATOR
    }

    fun getLastInteraction(): Interaction? {
        if (last_interactions.isNotEmpty()) {
            return last_interactions.sortedByDescending { it.getTimeInMillis() }[0]
        }
        return null
    }

    override fun equals(other: Any?) = this.id == (other as User).id
}
