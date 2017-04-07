package com.kazman.impraisechallenge.activity.details.list

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.kazman.impraisechallenge.Interaction

/**
 * Created by piotrkazmierczak on 06.04.2017.
 */
class InteractionAdapter: RecyclerView.Adapter<InteractionAdapter.InteractionViewHolder>() {

    private val items = mutableListOf<Interaction>()

    override fun onBindViewHolder(holder: InteractionViewHolder?, position: Int) {
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InteractionViewHolder {
        return InteractionViewHolder(parent?.rootView)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class InteractionViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}
