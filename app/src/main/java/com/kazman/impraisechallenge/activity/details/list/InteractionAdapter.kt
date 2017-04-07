package com.kazman.impraisechallenge.activity.details.list

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kazman.impraisechallenge.Interaction
import com.kazman.impraisechallenge.R
import com.kazman.impraisechallenge.activity.AbstractListAdapter
import com.kazman.impraisechallenge.databinding.ItemInteractionBinding
import com.kazman.impraisechallenge.getRelativeTimeSpanString
import com.kazman.impraisechallenge.getString

/**
 * Created by piotrkazmierczak on 06.04.2017.
 */
class InteractionAdapter : AbstractListAdapter<Interaction, InteractionAdapter.InteractionViewHolder>() {


    override fun onBindViewHolder(holder: InteractionViewHolder?, position: Int) {
        holder?.binding?.feedbackInfo = getString(R.string.ich_last_feedback) + items[position].getRelativeTimeSpanString()
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): InteractionViewHolder {
        val binding = DataBindingUtil.inflate<ItemInteractionBinding>(LayoutInflater.from(parent?.context), R.layout.item_interaction, parent, false)
        return InteractionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class InteractionViewHolder(itemView: ItemInteractionBinding) : RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
    }
}
