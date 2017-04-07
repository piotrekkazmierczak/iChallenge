package com.kazman.impraisechallenge.activity

import android.support.v7.widget.RecyclerView

/**
 * Created by piotrkazmierczak on 07.04.2017.
 */
abstract class AbstractListAdapter<T, S : RecyclerView.ViewHolder> : RecyclerView.Adapter<S>() {
    protected val items = mutableListOf<T>()

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    fun addItems(list: List<T>) {
        items.addAll(0, list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size
}
